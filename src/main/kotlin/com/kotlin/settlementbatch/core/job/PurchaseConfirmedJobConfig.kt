package com.kotlin.settlementbatch.core.job

import com.kotlin.settlementbatch.domain.entity.SettlementDaily
import com.kotlin.settlementbatch.domain.entity.claim.ClaimItem
import com.kotlin.settlementbatch.domain.entity.order.OrderItem
import com.kotlin.settlementbatch.infrastructure.database.repository.OrderItemRepository
import com.kotlin.settlementbatch.infrastructure.database.repository.SettlementDailyRepository
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableBatchProcessing
@EnableTransactionManagement
class PurchaseConfirmedJobConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
    @Qualifier("deliveryCompletedJpaItemReader") private val deliveryCompletedJpaItemReader: JpaPagingItemReader<OrderItem>,
    @Qualifier("dailySettlementJpaItemReader") private val dailySettlementJpaItemReader: JpaPagingItemReader<OrderItem>,
    @Qualifier("claimSettlementJpaItemReader") private val claimSettlementJpaItemReader: JpaPagingItemReader<ClaimItem>,
    private val orderItemRepository: OrderItemRepository,
    private val settlementDailyRepository: SettlementDailyRepository,
) {
    @Suppress("ktlint:standard:property-naming")
    val JOB_NAME = "purchaseConfirmedJob"
    val chunkSize = 500

    @Bean
    fun purchaseConfirmedJob(): Job =
        JobBuilder(JOB_NAME, jobRepository)
            .start(purchaseConfirmedStep())
            .next(dailySettlementJobStep())
            .next(claimSettlementJobStep())
            .build()

    @Bean
    @JobScope
    fun purchaseConfirmedStep(): Step =
        StepBuilder(JOB_NAME + "_step", jobRepository)
            .chunk<OrderItem, OrderItem>(this.chunkSize, transactionManager)
            .reader(deliveryCompletedJpaItemReader)
            .writer(purChaseConfirmedItemWriter())
            .build()

    @Bean
    fun purChaseConfirmedItemWriter(): PurchaseConfirmedWriter = PurchaseConfirmedWriter(orderItemRepository)

    @Bean
    @JobScope
    fun dailySettlementJobStep(): Step =
        StepBuilder(JOB_NAME + "_dailySettlement_step", jobRepository)
            .chunk<OrderItem, SettlementDaily>(chunkSize, transactionManager)
            .reader(dailySettlementJpaItemReader)
            .processor(dailySettlementItemProcessor())
            .writer(dailySettlementWriter())
            .build()

    @Bean
    fun dailySettlementItemProcessor(): DailySettlementItemProcessor = DailySettlementItemProcessor()

    @Bean
    fun dailySettlementWriter(): DailySettlementItemWriter = DailySettlementItemWriter(settlementDailyRepository)

    @Bean
    @JobScope
    fun claimSettlementJobStep(): Step =
        StepBuilder(JOB_NAME + "_claimSettlement_step", jobRepository)
            .chunk<ClaimItem, SettlementDaily>(chunkSize, transactionManager)
            .reader(claimSettlementJpaItemReader)
            .processor(claimSettlementItemProcessor())
            .writer(claimSettlementWriter())
            .build()

    @Bean
    fun claimSettlementItemProcessor(): ClaimSettlementItemProcessor = ClaimSettlementItemProcessor()

    @Bean
    fun claimSettlementWriter(): ClaimSettlementItemWriter = ClaimSettlementItemWriter(settlementDailyRepository)
}
