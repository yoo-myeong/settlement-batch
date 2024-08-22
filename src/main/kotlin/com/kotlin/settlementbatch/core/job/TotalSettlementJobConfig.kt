package com.kotlin.settlementbatch.core.job

import com.kotlin.settlementbatch.domain.SummingSettlementResponse
import com.kotlin.settlementbatch.domain.entity.settlement.SettlementTotal
import com.kotlin.settlementbatch.infrastructure.database.repository.SettlementTotalRepository
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
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
@EnableTransactionManagement
class TotalSettlementJobConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
    @Qualifier("totalSettlementJpaItemReader") private val totalSettlementJpaItemReader: JpaPagingItemReader<SummingSettlementResponse>,
    private val settlementTotalRepository: SettlementTotalRepository,
) {
    @Suppress("ktlint:standard:property-naming")
    private val JOB_NAME = "totalSettlementJob"

    private val chunkSize = 500

    @Bean
    fun totalSettlementJob(): Job =
        JobBuilder(JOB_NAME, jobRepository)
            .start(totalSettlementJobStep())
            .build()

    @Bean
    fun totalSettlementJobStep(): Step =
        StepBuilder(JOB_NAME + "_step", this.jobRepository)
            .chunk<SummingSettlementResponse, SettlementTotal>(this.chunkSize, this.transactionManager)
            .reader(totalSettlementJpaItemReader)
            .processor(totalSettlementItemProcessor())
            .writer(totalSettlementItemWriter())
            .build()

    @Bean
    fun totalSettlementItemProcessor() = TotalSettlementItemProcessor()

    @Bean
    fun totalSettlementItemWriter() = TotalSettlementItemWriter(settlementTotalRepository)
}
