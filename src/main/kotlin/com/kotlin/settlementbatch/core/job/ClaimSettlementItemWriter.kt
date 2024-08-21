package com.kotlin.settlementbatch.core.job

import com.kotlin.settlementbatch.domain.entity.SettlementDaily
import com.kotlin.settlementbatch.infrastructure.database.repository.SettlementDailyRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.context.annotation.Configuration

@Configuration
class ClaimSettlementItemWriter(
    private val settlementDailyRepository: SettlementDailyRepository,
) : ItemWriter<SettlementDaily> {
    override fun write(chunk: Chunk<out SettlementDaily>) {
        for (item in chunk.items) {
            settlementDailyRepository.save(item)
        }
    }
}
