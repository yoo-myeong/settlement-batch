package com.kotlin.settlementbatch.domain.entity.order

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.ZonedDateTime

@Entity
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_no") val id: Long? = null,
    val createdAt: ZonedDateTime? = ZonedDateTime.now(),
    val updatedAt: ZonedDateTime? = ZonedDateTime.now(),
    val deletedAt: ZonedDateTime? = null,
    val paidConfirmedAt: ZonedDateTime? = null,
    val paidPgAmount: BigDecimal? = BigDecimal.ZERO,
    val usedMileageAmount: BigDecimal? = BigDecimal.ZERO,
    val couponDiscountAmount: BigDecimal? = BigDecimal.ZERO,
    // 주문자 정보는 x
)
