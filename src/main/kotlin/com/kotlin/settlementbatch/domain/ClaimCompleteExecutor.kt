package com.kotlin.settlementbatch.domain

interface ClaimCompleteExecutor {
    fun updateCompleteAt(claimNo: Long)
}
