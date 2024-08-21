package com.kotlin.settlementbatch.core.job

import com.kotlin.settlementbatch.domain.collection.NegativeDailySettlementCollection
import com.kotlin.settlementbatch.domain.entity.SettlementDaily
import com.kotlin.settlementbatch.domain.entity.claim.ClaimItem
import org.springframework.batch.item.ItemProcessor

class ClaimSettlementItemProcessor : ItemProcessor<ClaimItem, SettlementDaily> {
    override fun process(item: ClaimItem): SettlementDaily = NegativeDailySettlementCollection(item).getSettlementDaily()
}
