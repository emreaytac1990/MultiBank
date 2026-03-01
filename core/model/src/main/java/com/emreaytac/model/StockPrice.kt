package com.emreaytac.model

import java.math.BigDecimal

enum class PriceDirection { UP, DOWN, SAME }


data class StockPrice(
    val symbol: String,
    val symbolName: String,
    val symbolDescription: String,
    val price: BigDecimal,
    val direction: PriceDirection = PriceDirection.SAME,
    val percentageChange: BigDecimal = BigDecimal.ZERO,
    val timestamp: Long = System.currentTimeMillis()
)