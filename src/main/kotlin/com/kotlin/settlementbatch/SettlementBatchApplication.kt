package com.kotlin.settlementbatch

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableBatchProcessing
class SettlementBatchApplication

fun main(args: Array<String>) {
    runApplication<SettlementBatchApplication>(*args)
}
