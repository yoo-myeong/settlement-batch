package com.kotlin.settlementbatch.infrastructure.database.repository

import com.kotlin.settlementbatch.domain.entity.settlement.SettlementTotal
import org.springframework.data.jpa.repository.JpaRepository

interface SettlementTotalRepository : JpaRepository<SettlementTotal, Long>
