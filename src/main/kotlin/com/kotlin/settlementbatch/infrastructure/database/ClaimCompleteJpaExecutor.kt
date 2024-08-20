package com.kotlin.settlementbatch.infrastructure.database

import com.kotlin.settlementbatch.domain.ClaimCompleteExecutor
import com.kotlin.settlementbatch.domain.enums.ClaimStatus
import com.kotlin.settlementbatch.infrastructure.database.repository.ClaimReceiptRepository
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class ClaimCompleteJpaExecutor(
    private val claimReceiptRepository: ClaimReceiptRepository,
) : ClaimCompleteExecutor {
    override fun updateCompleteAt(claimNo: Long) {
        val claimReceipt = claimReceiptRepository.findById(claimNo).get()

        val updateReceipt = claimReceipt.copy(id = claimNo, completedAt = ZonedDateTime.now(), claimStatus = ClaimStatus.COMPLETED)

        claimReceiptRepository.save(updateReceipt)
    }
}
