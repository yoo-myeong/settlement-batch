package com.kotlin.settlementbatch.interfaces.controller

import com.kotlin.settlementbatch.domain.ClaimCompleteService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/claim/complete")
class ClaimCompleteController(
    private val claimCompleteService: ClaimCompleteService,
) {
    @PutMapping("/claim_no/{claimNo}")
    fun completedOrder(
        @PathVariable claimNo: Long,
    ) {
        claimCompleteService.complete(claimNo)
    }
}
