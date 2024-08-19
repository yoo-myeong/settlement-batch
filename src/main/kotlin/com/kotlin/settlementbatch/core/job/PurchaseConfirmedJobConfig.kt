package com.kotlin.settlementbatch.core.job

import com.kotlin.settlementbatch.domain.entity.order.OrderItem
import com.kotlin.settlementbatch.infrastructure.database.repository.OrderItemRepository
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.data.RepositoryItemReader
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
    @Qualifier("deliveryCompletedJpaItemReader") private val deliveryCompletedJpaItemReader: RepositoryItemReader<OrderItem>,
    private val orderItemRepository: OrderItemRepository,
) {
    @Suppress("ktlint:standard:property-naming")
    val JOB_NAME = "purchaseConfirmedJob"
    val chunkSize = 500

    @Bean
    fun purchaseConfirmedJob(): Job =
        JobBuilder(JOB_NAME, jobRepository)
            .start(purchaseConfirmedStep())
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
}
