package com.kotlin.settlementbatch.domain.enums

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

enum class ClaimType(
    val value: String,
) {
    CANCELED("CANCELED"),
    EXCHANGE("EXCHANGE"),
    RETURN("RETURN"),
}

@Converter(autoApply = true)
class ClaimTypeConverter : AttributeConverter<ClaimType, String> {
    override fun convertToDatabaseColumn(attribute: ClaimType?): String =
        attribute?.value ?: throw IllegalArgumentException("Invalid ClaimStatus value")

    override fun convertToEntityAttribute(dbData: String?): ClaimType =
        when (dbData) {
            "CANCELED" -> ClaimType.CANCELED
            "EXCHANGE" -> ClaimType.EXCHANGE
            "RETURN" -> ClaimType.RETURN
            else -> throw IllegalArgumentException("Invalid ClaimStatus value: $dbData")
        }
}
