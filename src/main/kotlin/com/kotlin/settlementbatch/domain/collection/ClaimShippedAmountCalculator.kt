package com.kotlin.settlementbatch.domain.collection

import com.kotlin.settlementbatch.domain.entity.claim.ClaimItem
import com.kotlin.settlementbatch.domain.enums.ClaimType
import com.kotlin.settlementbatch.domain.enums.ExtraFeePayer
import java.math.BigDecimal

class ClaimShippedAmountCalculator(
    private val item: ClaimItem,
) {
    @Suppress("ktlint:standard:property-naming")
    private val SHIPPING_AMOUNT: BigDecimal = BigDecimal.valueOf(3000L)

    fun getClaimShippedAmount(): BigDecimal {
        val claimReceipt = item.claimReceipt
        val shippingCount = getShippingCount(claimReceipt.requestType, claimReceipt.extraFeePayer) // 취소는 0, 교환 2, 반품 1

        return SHIPPING_AMOUNT.multiply(shippingCount.toBigDecimal())
    }

    private fun getShippingCount(
        requestType: ClaimType,
        extraFeePayer: ExtraFeePayer,
    ): Int {
        if (extraFeePayer == ExtraFeePayer.BUYER) {
            return 0
        }

        // 취소는 0, 교환 2, 반품 1
        return when (requestType) {
            ClaimType.CANCELED -> 0
            ClaimType.EXCHANGE -> -2
            ClaimType.RETURN -> -1
        }
    }
}
