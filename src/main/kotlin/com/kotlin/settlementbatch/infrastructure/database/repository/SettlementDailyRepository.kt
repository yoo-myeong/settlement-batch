package com.kotlin.settlementbatch.infrastructure.database.repository

import com.kotlin.settlementbatch.domain.entity.SettlementDaily
import org.springframework.data.jpa.repository.JpaRepository

interface SettlementDailyRepository : JpaRepository<SettlementDaily, Long>
