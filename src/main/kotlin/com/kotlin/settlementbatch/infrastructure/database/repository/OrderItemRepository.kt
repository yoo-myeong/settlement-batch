package com.kotlin.settlementbatch.infrastructure.database.repository

import com.kotlin.settlementbatch.domain.entity.order.OrderItem
import org.springframework.data.jpa.repository.JpaRepository
import java.time.ZonedDateTime

interface OrderItemRepository : JpaRepository<OrderItem, Long> {
    fun findByShippedCompleteAtBetween(
        startDateTime: ZonedDateTime,
        endDateTime: ZonedDateTime,
    ): List<OrderItem>
}
