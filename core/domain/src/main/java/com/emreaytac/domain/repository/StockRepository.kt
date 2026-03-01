package com.emreaytac.domain.repository

import com.emreaytac.model.StockPrice
import com.emreaytac.websocket.SocketStatus
import kotlinx.coroutines.flow.StateFlow

interface StockRepository {
    val stockPrices: StateFlow<List<StockPrice>>
    val connectionStatus: StateFlow<SocketStatus>
    fun startPriceFeed()
    fun stopPriceFeed()
    fun getStockBySymbol(symbol: String): StockPrice?
}