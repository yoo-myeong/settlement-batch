package com.kotlin.settlementbatch.domain.entity.order

import com.kotlin.settlementbatch.domain.entity.Product
import com.kotlin.settlementbatch.domain.entity.Seller
import com.kotlin.settlementbatch.domain.enums.TaxType
import com.kotlin.settlementbatch.domain.enums.TaxTypeConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.math.BigDecimal
import java.time.ZonedDateTime

@Entity
data class OrderItemSnapshot(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_snapshot_no")
    val id: Long? = null,
    val createdAt: ZonedDateTime? = ZonedDateTime.now(),
    val updatedAt: ZonedDateTime? = ZonedDateTime.now(),
    val deletedAt: ZonedDateTime? = null,
    @Column(name = "product_no")
    val productNo: Long,
    @Column(name = "seller_no")
    val sellerNo: Long,
    val sellPrice: BigDecimal? = BigDecimal.ZERO,
    val supplyPrice: BigDecimal? = BigDecimal.ZERO,
    val promotionAmount: BigDecimal? = BigDecimal.ZERO,
    val defaultDeliveryAmount: BigDecimal? = BigDecimal.valueOf(3000),
    val mileageUsageAmount: BigDecimal? = BigDecimal.ZERO,
    val itemCategory: Int? = 0, // TODO : Enum으로 변경
    val taxRate: Int? = 3,
    @Convert(converter = TaxTypeConverter::class)
    val taxType: TaxType,
    @ManyToOne
    @JoinColumn(name = "seller_no", insertable = false, updatable = false)
    val seller: Seller,
    @ManyToOne
    @JoinColumn(name = "product_no", insertable = false, updatable = false)
    val product: Product,
)
