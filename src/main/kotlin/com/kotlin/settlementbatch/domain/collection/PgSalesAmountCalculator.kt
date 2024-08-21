package com.kotlin.settlementbatch.domain.collection

import com.kotlin.settlementbatch.domain.entity.order.OrderItemSnapshot
import java.math.BigDecimal

class PgSalesAmountCalculator(
    private val orderItemSnapshot: OrderItemSnapshot,
) {
    fun getPgSaleAmount(): BigDecimal {
        val price = orderItemSnapshot.sellPrice ?: BigDecimal.ZERO

        return price
            .subtract(orderItemSnapshot.promotionAmount)
            .subtract(orderItemSnapshot.mileageUsageAmount)
    }
}
