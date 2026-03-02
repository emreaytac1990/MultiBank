package com.emreaytac.stock.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun FeedScreen(
    onStockClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = hiltViewModel()
){
    // val viewModel: FeedViewModel = viewModel()
    val conn = viewModel.connectionStatus.collectAsStateWithLifecycle()
    val list = viewModel.uiState.collectAsStateWithLifecycle()

    Column() {
        if (list.value.stockPrices != null && list.value.stockPrices.isNotEmpty()){
            Text("${list.value.stockPrices.first().symbol} - ${list.value.stockPrices.first().price}")
        }

        Text(conn.value.toString())
    }
    //val st = viewModel.settingsUiState.collectAsStateWithLifecycle()
    //Text("st.value.adasd.toString()")
}