package com.kotlin.settlementbatch.core.job

import com.kotlin.settlementbatch.domain.collection.PositiveDailySettlementCollection
import com.kotlin.settlementbatch.domain.entity.SettlementDaily
import com.kotlin.settlementbatch.domain.entity.order.OrderItem
import org.springframework.batch.item.ItemProcessor

class DailySettlementItemProcessor : ItemProcessor<OrderItem, SettlementDaily> {
    override fun process(item: OrderItem): SettlementDaily {
        val positiveDailySettlementCollection = PositiveDailySettlementCollection(item)

        return positiveDailySettlementCollection.getSettlementDaily()
    }
}
