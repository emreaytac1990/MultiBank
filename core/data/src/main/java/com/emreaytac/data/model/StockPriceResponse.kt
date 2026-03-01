package com.emreaytac.data.model

import com.emreaytac.data.utils.BigDecimalSerializer
import com.emreaytac.model.PriceDirection
import com.emreaytac.model.StockPrice
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.math.RoundingMode

@Serializable
data class StockPriceResponse(
    @SerialName("symbol") val symbol: String,

    @SerialName("symbolName") val symbolName: String,

    @SerialName("symbolDescription") val symbolDescription: String,

    @Serializable(with = BigDecimalSerializer::class)
    @SerialName("price") val price: BigDecimal,

    @SerialName("timestamp") val timestamp: Long
)

fun StockPriceResponse.toDomain(previousPrice: BigDecimal?): StockPrice {
    val direction = when {
        previousPrice == null -> PriceDirection.SAME
        price > previousPrice -> PriceDirection.UP
        price < previousPrice -> PriceDirection.DOWN
        else -> PriceDirection.SAME
    }

    val percentageChange = previousPrice?.let {
        ((price - it) / it * BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)
    } ?: BigDecimal.ZERO

    return StockPrice(
        symbol = symbol,
        symbolName = symbolName,
        symbolDescription = symbolDescription,
        price = price,
        direction = direction,
        percentageChange = percentageChange,
        timestamp = timestamp
    )
}