package com.emreaytac.stock.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.emreaytac.designsystem.component.StockCard
import com.emreaytac.designsystem.component.StockCardShimmer
import com.emreaytac.designsystem.component.TopBox
import com.emreaytac.designsystem.theme.LocalExtendedColors
import com.emreaytac.model.PriceDirection
import com.emreaytac.model.StockPrice
import com.emreaytac.stock.R
import com.emreaytac.websocket.SocketStatus

@Composable
fun FeedScreen(
    onStockClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = hiltViewModel()
){
    val conn = viewModel.connectionStatus.collectAsStateWithLifecycle()
    val list = viewModel.uiState.collectAsStateWithLifecycle()


    Column(
        modifier = modifier.padding(12.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TopBox(
                cornerRadius = 20.dp,
                mainColor = if (conn.value == SocketStatus.CONNECTED) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.error,
                borderColor = if (conn.value == SocketStatus.CONNECTED) LocalExtendedColors.current.topBoxBorderPositive else LocalExtendedColors.current.topBoxBorderNegative,
                backgroundColor = if (conn.value == SocketStatus.CONNECTED) LocalExtendedColors.current.topBoxBgPositive else LocalExtendedColors.current.topBoxBgNegative,
                isCircle = true,
                text = if (conn.value == SocketStatus.CONNECTED) stringResource(id= R.string.feature_stock_live) else stringResource(id= R.string.feature_stock_offline),
                height = 32.dp,
                isClickable = false
            )

            TopBox(
                cornerRadius = 20.dp,
                mainColor = if (conn.value == SocketStatus.CONNECTED) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.secondary,
                borderColor = if (conn.value == SocketStatus.CONNECTED) LocalExtendedColors.current.topBoxBorderNegative else LocalExtendedColors.current.topBoxBorderPositive,
                backgroundColor = if (conn.value == SocketStatus.CONNECTED) LocalExtendedColors.current.topBoxBgNegative else LocalExtendedColors.current.topBoxBgPositive,
                isCircle = false,
                text = if (conn.value == SocketStatus.CONNECTED) stringResource(id= R.string.feature_stock_stop_feed) else stringResource(id= R.string.feature_stock_start_feed),
                height = 38.dp
            ){
                viewModel.togglePriceFeed()
            }
        }

        Text(
            stringResource(id= R.string.feature_stock_markets),
            modifier = Modifier.padding(top = 18.dp, start = 8.dp, bottom = 30.dp),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.displaySmall
        )

        if (list.value.isLoading) {
            StockListLoading()
        } else if (list.value.stockPrices.isNotEmpty()) {
            StockList(
                isConnected = conn.value == SocketStatus.CONNECTED,
                stocks = list.value.stockPrices,
                onStockClick = onStockClick
            )
        }

    }
}

@Composable
fun StockList(
    isConnected: Boolean = true,
    stocks: List<StockPrice>,
    onStockClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        items(
            items = stocks,
            key = { it.id  },
            contentType = { "stock_card" }
        ) { stock ->


            StockCard(
                isConnected = isConnected,
                symbol = stock.symbol,
                stockName = stock.symbolName,
                price = stock.price.toPlainString(),
                ratio = stock.percentageChange.toPlainString(),
                isPositive = stock.direction == PriceDirection.UP,
                isSame = stock.direction == PriceDirection.SAME,
                modifier = Modifier.clickable { onStockClick(stock.symbol) }
            )
        }
    }
}


@Composable
fun StockListLoading(itemCount: Int = 10) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        userScrollEnabled = false
    ) {
        items(itemCount) {
            StockCardShimmer()
        }
    }
}
