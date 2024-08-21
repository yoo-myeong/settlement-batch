package com.kotlin.settlementbatch.domain.collection

import com.kotlin.settlementbatch.domain.TaxTypeItem
import com.kotlin.settlementbatch.domain.entity.order.OrderItemSnapshot
import com.kotlin.settlementbatch.domain.enums.TaxType
import java.math.BigDecimal

class TaxCalculator(
    private val orderItemSnapshot: OrderItemSnapshot,
) {
    @Suppress("ktlint:standard:property-naming")
    private val TAX_RATE = 0.03

    private fun getTaxTypeItem(): TaxTypeItem =
        when (orderItemSnapshot.taxType) {
            TaxType.TAX -> TaxTypeItem.TaxItem(orderItemSnapshot.sellPrice)
            TaxType.FREE -> TaxTypeItem.TaxFreeItem(orderItemSnapshot.sellPrice)
            TaxType.ZERO -> TaxTypeItem.ZeroTaxItem(orderItemSnapshot.sellPrice)
            null -> TaxTypeItem.TaxItem(orderItemSnapshot.sellPrice)
        }

    fun getTaxAmount(): BigDecimal {
        val taxTypeItem = getTaxTypeItem()

        return when (taxTypeItem) {
            is TaxTypeItem.TaxItem -> taxTypeItem.price!!.multiply(BigDecimal.valueOf(TAX_RATE))
            is TaxTypeItem.ZeroTaxItem -> taxTypeItem.price ?: BigDecimal.ZERO
            is TaxTypeItem.TaxFreeItem -> taxTypeItem.price ?: BigDecimal.ZERO
            else -> BigDecimal.ZERO
        }
    }

    private fun getCalculateTaxForTaxItem(taxItem: TaxTypeItem.TaxItem): BigDecimal {
        val price = taxItem.price ?: BigDecimal.ZERO
        return price.multiply(BigDecimal.valueOf(TAX_RATE))
    }
}
