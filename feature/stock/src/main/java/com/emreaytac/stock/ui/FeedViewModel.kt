package com.emreaytac.stock.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emreaytac.domain.repository.StockRepository
import com.emreaytac.domain.repository.UserDataRepository
import com.emreaytac.model.DarkThemeConfig
import com.emreaytac.model.StockPrice
import com.emreaytac.websocket.SocketStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val stockRepository: StockRepository,
    private val userDataRepository: UserDataRepository
): ViewModel(){

    val settingsUiState: StateFlow<Int> =
        userDataRepository.userData
            .map { userData ->
                userData.darkThemeConfig.id
            }
            .stateIn(
                scope = viewModelScope,
                started = WhileSubscribed(5.seconds.inWholeMilliseconds),
                initialValue = 0,
            )
    private val _isFeedActive = MutableStateFlow(false)
    val uiState: StateFlow<FeedUiState> = stockRepository.stockPrices
        .map { prices ->
            FeedUiState(
                stockPrices = prices,
                isLoading = prices.isEmpty(),
                isFeedActive = _isFeedActive.value
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = FeedUiState()
        )
    val connectionStatus: StateFlow<SocketStatus> = stockRepository.connectionStatus
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = SocketStatus.DISCONNECTED
        )

    private val _isManuallyStopped = MutableStateFlow(false)
    val isManuallyStopped: StateFlow<Boolean> = _isManuallyStopped.asStateFlow()

    fun togglePriceFeed() {
        if (_isFeedActive.value) {
            _isManuallyStopped.value = true
            disconnectWs()
        } else {
            _isManuallyStopped.value = false
            connectWs()
        }
    }

    fun setTheme(id: Int) {
        viewModelScope.launch {
            val theme = when (id) {
                DarkThemeConfig.FOLLOW_SYSTEM.id -> {
                    DarkThemeConfig.FOLLOW_SYSTEM
                }
                DarkThemeConfig.LIGHT.id -> {
                    DarkThemeConfig.LIGHT
                }
                else -> {
                    DarkThemeConfig.DARK
                }
            }
            userDataRepository.setDarkThemeConfig(theme)
        }
    }

    fun connectWs(){
        if (isManuallyStopped.value) return
        _isFeedActive.value = true
        stockRepository.startPriceFeed()
    }

    fun disconnectWs(){
        _isFeedActive.value = false
        stockRepository.stopPriceFeed()
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

