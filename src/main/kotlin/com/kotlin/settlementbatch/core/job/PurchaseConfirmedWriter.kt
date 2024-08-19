package com.kotlin.settlementbatch.core.job

import com.kotlin.settlementbatch.domain.entity.order.OrderItem
import com.kotlin.settlementbatch.infrastructure.database.repository.OrderItemRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.lang.NonNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Component
@Transactional
class PurchaseConfirmedWriter(
    private val orderItemRepository: OrderItemRepository,
) : ItemWriter<OrderItem> {
    override fun write(
        @NonNull chunk: Chunk<out OrderItem>,
    ) {
        for (item in chunk.items) {
            val newItem = item.copy(id = item.id, purchaseConfirmedAt = ZonedDateTime.now())
            orderItemRepository.save(newItem)
        }
    }
}
