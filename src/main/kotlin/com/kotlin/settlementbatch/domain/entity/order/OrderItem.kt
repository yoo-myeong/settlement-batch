package com.kotlin.settlementbatch.domain.entity.order

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import java.time.ZonedDateTime

@Entity
data class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_no") val id: Long? = null,
    val orderNo: Long, // 주문번호
    @Column(name = "order_item_snapshot_no")
    val orderItemSnapshotNo: Long, // 주문 스냅샷 번호
    val orderCount: Int? = 1, // 주문수량
    val itemDeliveryStatus: Int? = 0, // 주문 배송 상태
    val createdAt: ZonedDateTime? = ZonedDateTime.now(), // 생성시간
    val updatedAt: ZonedDateTime? = ZonedDateTime.now(), // 업데이트시간
    val deletedAt: ZonedDateTime? = null, // 삭제시간
    val purchaseConfirmedAt: ZonedDateTime? = null, // 구매확정일
    val shippedCompleteAt: ZonedDateTime? = null, // 배송완료일
    @OneToOne
    @JoinColumn(name = "order_item_snapshot_no", insertable = false, updatable = false)
    val orderItemSnapshot: OrderItemSnapshot,
)
