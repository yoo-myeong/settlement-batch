package com.kotlin.settlementbatch.domain.enums

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class ClaimStatusConverter : AttributeConverter<ClaimStatus, Int> {
    override fun convertToDatabaseColumn(attribute: ClaimStatus?): Int =
        attribute?.value ?: throw IllegalArgumentException("Invalid ClaimStatus value")

    override fun convertToEntityAttribute(dbData: Int?): ClaimStatus =
        when (dbData) {
            0 -> ClaimStatus.WITHDRAWN
            1 -> ClaimStatus.RECEIVED
            2 -> ClaimStatus.IN_PROGRESS
            3 -> ClaimStatus.COMPLETED
            else -> throw IllegalArgumentException("Invalid ClaimStatus value: $dbData")
        }
}
