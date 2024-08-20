package com.kotlin.settlementbatch.domain

import org.springframework.stereotype.Service

@Service
class ClaimCompleteService(
    private val executor: ClaimCompleteExecutor,
) {
    fun complete(claimNo: Long) = executor.updateCompleteAt(claimNo)
}
