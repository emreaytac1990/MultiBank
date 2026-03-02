package com.emreaytac.stock.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emreaytac.domain.repository.StockRepository
import com.emreaytac.domain.repository.UserDataRepository
import com.emreaytac.model.StockPrice
import com.emreaytac.websocket.SocketStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val stockRepository: StockRepository,
    userDataRepository: UserDataRepository
): ViewModel(){

    private val _uiState = MutableStateFlow(FeedUiState())
    val uiState: StateFlow<FeedUiState> = _uiState.asStateFlow()

    private val _connectionStatus = MutableStateFlow(SocketStatus.DISCONNECTED)
    val connectionStatus: StateFlow<SocketStatus> = _connectionStatus.asStateFlow()

    init {
        stockRepository.startPriceFeed()
        observeStockPrices()
        observeSocketStatus()
    }

    private fun observeStockPrices() {
        viewModelScope.launch {
            stockRepository.stockPrices.collect { prices ->
                if (prices.isNotEmpty()){
                    Log.e("FeedViewModel", "prices: ${prices.first().symbol} ")
                    _uiState.update { it.copy(
                        stockPrices = prices,
                        isLoading = false,
                        isFeedActive = true
                    )}
                }
            }
        }
    }

    private fun observeSocketStatus() {
        viewModelScope.launch {
            stockRepository.connectionStatus.collect { status ->
                _connectionStatus.update { status }
            }
        }
    }

    fun togglePriceFeed() {
        if (_uiState.value.isFeedActive) {
            stockRepository.stopPriceFeed()
            _uiState.update { it.copy(isFeedActive = false) }
        } else {
            stockRepository.startPriceFeed()
            _uiState.update { it.copy(isFeedActive = true) }
        }
    }

    data class FeedUiState(
        val stockPrices: List<StockPrice> = emptyList(),
        val isLoading: Boolean = true,
        val isFeedActive: Boolean = false
    )

    override fun onCleared() {
        super.onCleared()
        stockRepository.stopPriceFeed()
    }
}

