package com.kotlin.settlementbatch.core.job

import com.kotlin.settlementbatch.domain.entity.settlement.SettlementTotal
import com.kotlin.settlementbatch.infrastructure.database.repository.SettlementTotalRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter

class TotalSettlementItemWriter(
    private val settlementTotalRepository: SettlementTotalRepository,
) : ItemWriter<SettlementTotal> {
    override fun write(chunk: Chunk<out SettlementTotal>) {
        for (item in chunk.items) {
            settlementTotalRepository.save(item)
        }
    }
}
