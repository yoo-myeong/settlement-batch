package com.kotlin.settlementbatch.infrastructure.database.repository

import com.kotlin.settlementbatch.domain.entity.order.OrderItemSnapshot
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemSnapshotRepository : JpaRepository<OrderItemSnapshot, Long>
