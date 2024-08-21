package com.kotlin.settlementbatch.domain.entity.claim

import com.kotlin.settlementbatch.domain.entity.order.OrderItem
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import java.time.ZonedDateTime

@Entity
data class ClaimItem(
    @Id @Column(name = "claim_item_no") val id: Long,
    @Column(name = "claim_receipt_no")
    val claimReceiptNo: Long,
    val createdAt: ZonedDateTime? = ZonedDateTime.now(),
    val updatedAt: ZonedDateTime? = ZonedDateTime.now(),
    val deletedAt: ZonedDateTime? = null,
    @Column(name = "order_item_no")
    val orderItemNo: Long,
    val claimCount: Int? = 1,
    @OneToOne
    @JoinColumn(name = "order_item_no", insertable = false, updatable = false)
    val orderItem: OrderItem,
    @ManyToOne
    @JoinColumn(name = "claim_receipt_no", insertable = false, updatable = false)
    val claimReceipt: ClaimReceipt,
)
