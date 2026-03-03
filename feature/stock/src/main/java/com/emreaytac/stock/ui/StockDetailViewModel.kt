package com.emreaytac.stock.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emreaytac.domain.repository.StockRepository
import com.emreaytac.model.StockPrice
import com.emreaytac.websocket.SocketStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class StockDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val stockRepository: StockRepository
): ViewModel(){

    val symbol: String = checkNotNull(savedStateHandle["symbol"])
    private val _isFeedActive = MutableStateFlow(false)

    val uiState: StateFlow<StockDetailState> = stockRepository.stockPrices
        .mapNotNull { prices ->
            val price = stockRepository.getStockBySymbol(symbol)
            price?.let {
                StockDetailState(
                    stockPrice = price,
                    isLoading = prices.isEmpty(),
                    isFeedActive = _isFeedActive.value
                )
            }

        }
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = StockDetailState(null)
        )

    val connectionStatus: StateFlow<SocketStatus> = stockRepository.connectionStatus
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = SocketStatus.DISCONNECTED
        )

    fun connectWs(){
        _isFeedActive.value = true
        stockRepository.startPriceFeed()
    }

    fun disconnectWs(){
        _isFeedActive.value = false
        stockRepository.stopPriceFeed()
    }

    data class StockDetailState(
        val stockPrice: StockPrice?,
        val isLoading: Boolean = true,
        val isFeedActive: Boolean = false
    )

}