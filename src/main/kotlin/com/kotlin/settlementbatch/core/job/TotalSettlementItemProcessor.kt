package com.kotlin.settlementbatch.core.job

import com.kotlin.settlementbatch.domain.SummingSettlementResponse
import com.kotlin.settlementbatch.domain.entity.settlement.SettlementTotal
import org.springframework.batch.item.ItemProcessor
import java.time.LocalDate

class TotalSettlementItemProcessor : ItemProcessor<SummingSettlementResponse, SettlementTotal> {
    override fun process(item: SummingSettlementResponse): SettlementTotal =
        SettlementTotal(
            settlementDate = LocalDate.now(),
            sellerNo = item.sellerNo,
            sellerName = item.sellerName,
            sellerBusinessNumber = item.sellerBusinessNumber,
            sellType = item.sellType,
            taxType = item.taxType,
            pgSalesAmount = item.sumPgSalesAmount,
            couponDiscountAmount = item.sumCouponDiscountAmount,
            mileageUsageAmount = item.sumMileageUsageAmount,
            shippingFeeAmount = item.sumShippingFeeAmount,
            claimShippingFeeAmount = item.sumClaimShippingFeeAmount,
            commissionAmount = item.sumCommissionAmount,
            taxAmount = item.sumTaxAmount,
        )
}
