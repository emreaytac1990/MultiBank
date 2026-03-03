package com.emreaytac.data

import com.emreaytac.data.model.StockPriceResponse
import com.emreaytac.data.model.toDomain
import com.emreaytac.model.PriceDirection
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class StockPriceResponseTest {

    @Test
    fun when_price_increases_direction_should_be_UP_and_percentage_correct() {
        val previousPrice = BigDecimal("100.00")
        val response = StockPriceResponse(
            id = "1", symbol = "AAPL", symbolName = "Apple",
            symbolDescription = "Desc", price = BigDecimal("110.00"), timestamp = 0L
        )

        val domainModel = response.toDomain(previousPrice)

        assertEquals(PriceDirection.UP, domainModel.direction)
        assertEquals(BigDecimal("10.00"), domainModel.percentageChange)
    }

    @Test
    fun when_price_decreases_direction_should_be_DOWN() {
        val previousPrice = BigDecimal("100.00")
        val response = StockPriceResponse(
            id = "1", symbol = "AAPL", price = BigDecimal("90.00"),
            symbolName = "Apple", symbolDescription = "Desc", timestamp = 0L
        )

        val domainModel = response.toDomain(previousPrice)

        assertEquals(PriceDirection.DOWN, domainModel.direction)
        assertEquals(BigDecimal("-10.00"), domainModel.percentageChange)
    }
}