package com.kotlin.settlementbatch.infrastructure.database.repository

import com.kotlin.settlementbatch.domain.entity.claim.ClaimItem
import org.springframework.data.jpa.repository.JpaRepository

interface ClaimItemRepository : JpaRepository<ClaimItem, Long>
