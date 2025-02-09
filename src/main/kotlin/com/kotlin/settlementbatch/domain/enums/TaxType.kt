package com.kotlin.settlementbatch.domain.enums

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

enum class TaxType(
    val value: String,
) {
    TAX("TAX"),
    ZERO("ZERO"),
    FREE("FREE"),
}

@Converter(autoApply = true)
class TaxTypeConverter : AttributeConverter<TaxType, String> {
    override fun convertToDatabaseColumn(attribute: TaxType?): String =
        attribute?.value ?: throw IllegalArgumentException("Invalid ClaimStatus value")

    override fun convertToEntityAttribute(dbData: String?): TaxType =
        when (dbData) {
            "TAX" -> TaxType.TAX
            "ZERO" -> TaxType.ZERO
            "FREE" -> TaxType.FREE
            else -> throw IllegalArgumentException("Invalid ClaimStatus value: $dbData")
        }
}
