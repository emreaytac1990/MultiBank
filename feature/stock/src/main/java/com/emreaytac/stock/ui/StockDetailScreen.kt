package com.emreaytac.stock.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emreaytac.designsystem.component.StockDetailShimmer
import com.emreaytac.designsystem.theme.LocalExtendedColors
import com.emreaytac.model.PriceDirection
import com.emreaytac.stock.R

@Composable
fun StockDetailScreen(
    symbol: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: StockDetailViewModel = hiltViewModel()
){
    val conn = viewModel.connectionStatus.collectAsStateWithLifecycle()
    val stock = viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current
    LifecycleResumeEffect(Unit, lifecycleOwner) {
        viewModel.connectWs()

        onPauseOrDispose {
            viewModel.disconnectWs()
        }
    }


    if (stock.value.isLoading) {
        StockDetailShimmer()
    } else if (stock.value.stockPrice != null) {
        val mStock = stock.value.stockPrice!!
        val isPositive = mStock.direction == PriceDirection.UP
        val trendIcon = if (isPositive) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
        val trendColor = if (isPositive) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.error

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp).clickable{
                    onBack()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = stringResource(R.string.feature_stock_markets),
                    color = LocalExtendedColors.current.cardMainText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }


            Text(
                text = symbol,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = LocalExtendedColors.current.cardMainText,
            )
            Text(
                text = mStock.symbolName,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primaryFixed,
                modifier = Modifier.padding(bottom = 24.dp)
            )


            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .border(1.dp, LocalExtendedColors.current.cardBorder, RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = stringResource(R.string.feature_stock_current_price),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "$${mStock.price.toPlainString()}",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = LocalExtendedColors.current.cardMainText,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = trendIcon,
                            contentDescription = null,
                            tint = trendColor,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "${if (isPositive) "+" else ""}${mStock.percentageChange}%",
                            color = trendColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .border(1.dp, LocalExtendedColors.current.cardBorder, RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = stringResource(R.string.feature_stock_about),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = mStock.symbolDescription,
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        color = LocalExtendedColors.current.cardMainText
                    )
                }
            }
        }


    }



}