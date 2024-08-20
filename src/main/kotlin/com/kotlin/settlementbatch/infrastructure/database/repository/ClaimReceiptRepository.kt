package com.kotlin.settlementbatch.infrastructure.database.repository

import com.kotlin.settlementbatch.domain.entity.claim.ClaimReceipt
import org.springframework.data.jpa.repository.JpaRepository

interface ClaimReceiptRepository : JpaRepository<ClaimReceipt, Long>
