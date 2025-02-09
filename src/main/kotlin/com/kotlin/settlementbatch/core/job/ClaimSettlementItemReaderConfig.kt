package com.kotlin.settlementbatch.core.job

import com.kotlin.settlementbatch.domain.entity.claim.ClaimItem
import jakarta.persistence.EntityManager
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ClaimSettlementItemReaderConfig(
    private val entityManager: EntityManager,
) {
    val chunkSize = 500

    @Bean
    fun claimSettlementJpaItemReader(): JpaPagingItemReader<ClaimItem> {
        val queryProvider = CustomClaimItemQueryProvider()

        return JpaPagingItemReaderBuilder<ClaimItem>()
            .name("claimSettlementJpaItemReader")
            .entityManagerFactory(this.entityManager.entityManagerFactory)
            .pageSize(this.chunkSize)
            .queryProvider(queryProvider)
            .build()
    }
}
