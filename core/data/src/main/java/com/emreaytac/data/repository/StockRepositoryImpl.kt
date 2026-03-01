package com.emreaytac.data.repository

import android.util.Log
import com.emreaytac.data.model.StockPriceResponse
import com.emreaytac.data.model.toDomain
import com.emreaytac.data.utils.generateMockList
import com.emreaytac.di.Dispatchers
import com.emreaytac.domain.repository.StockRepository
import com.emreaytac.model.StockPrice
import com.emreaytac.websocket.SocketManager
import com.emreaytac.websocket.SocketStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import java.math.BigDecimal

@Singleton
class StockRepositoryImpl @Inject constructor(private val socketManager: SocketManager,
                                              @Dispatchers.IO private val dispatcher: CoroutineDispatcher): StockRepository {

    private val _stockPrices = MutableStateFlow<List<StockPrice>>(emptyList())
    override val stockPrices: StateFlow<List<StockPrice>> = _stockPrices.asStateFlow()

    override val connectionStatus: StateFlow<SocketStatus> = socketManager.observeStatus()

    private val scope = CoroutineScope(dispatcher + SupervisorJob())
    private var priceUpdateJob: Job? = null



    init {
        listenWs()
    }


    private fun listenWs() {
        scope.launch {
            socketManager.observeMessages().conflate().collect { res ->
                try {
                    val stockList = Json.decodeFromString<List<StockPriceResponse>>(res)

                    updateStockPrices(stockList)

                } catch (e: Exception) {
                    Log.e("StockRepository", "Error parsing message: ${e.message}")
                }
            }
        }
    }

    private fun updateStockPrices(newStockResponses: List<StockPriceResponse>) {
        _stockPrices.update { currentList ->
            val updatedPrices  = newStockResponses.map { item ->
                val previousPrice = currentList.find { it.symbol == item.symbol }?.price ?: BigDecimal.ZERO

                item.toDomain(previousPrice)
            }
            updatedPrices.sortedBy { it.symbol }
        }
    }


    override fun startPriceFeed() {
        // TODO: Get url from config
        socketManager.connect("wss://ws.postman-echo.com/raw")

        priceUpdateJob?.cancel()
        priceUpdateJob = scope.launch {
            while (isActive) {
                // Create price list for all symbols
                val priceList = generateMockList()

                val message = Json.encodeToString(priceList)
                socketManager.sendMessage(message){
                    Log.e("StockRepository", "Error sending message: ${it.message}")
                }

                delay(2000)
            }
        }
    }



    override fun stopPriceFeed() {
        priceUpdateJob?.cancel()
        priceUpdateJob = null
        socketManager.disconnect()
    }

    override fun getStockBySymbol(symbol: String): StockPrice? {
        return _stockPrices.value.find { it.symbol == symbol }
    }

}