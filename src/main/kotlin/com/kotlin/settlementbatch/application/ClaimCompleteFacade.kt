package com.kotlin.settlementbatch.application

import com.kotlin.settlementbatch.domain.ClaimCompleteService
import org.springframework.stereotype.Service

@Service
class ClaimCompleteFacade(
    private val claimCompleteService: ClaimCompleteService,
) {
    fun complete(claimNo: Long) {
        claimCompleteService.complete(claimNo)
    }
}
