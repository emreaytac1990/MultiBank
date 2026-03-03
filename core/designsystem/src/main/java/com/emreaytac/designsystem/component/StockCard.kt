package com.emreaytac.designsystem.component

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.emreaytac.designsystem.theme.LocalExtendedColors
import com.emreaytac.designsystem.theme.MBTypography
import com.emreaytac.designsystem.theme.MultiBankTheme
import kotlinx.coroutines.delay

@Composable
fun StockCard(
    modifier: Modifier = Modifier,
    symbol: String,
    price: String,
    stockName: String,
    ratio: String,
    isPositive: Boolean,
    isSame: Boolean = false,
    cornerRadius: Dp = 12.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    borderColor: Color = LocalExtendedColors.current.cardBorder,
    isConnected: Boolean = true
) {
    val defaultColor = LocalExtendedColors.current.cardMainText
    val highlightColor = if (isPositive) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.error
    val priceColor = remember { Animatable(defaultColor) }


    LaunchedEffect(key1 = price) {
        if (!isSame && isConnected) {
            priceColor.animateTo(highlightColor, animationSpec = tween(300))
            delay(1000)
            priceColor.animateTo(defaultColor, animationSpec = tween(500))
        }
    }


    val trendColor = if (isSame) defaultColor else highlightColor
    val trendIcon = if (isPositive) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown


    val prefix = when {
        isSame -> ""
        isPositive -> "+"
        else -> ""
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(cornerRadius))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(cornerRadius))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Symbol and Price
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = symbol,
                    color = LocalExtendedColors.current.cardMainText,
                    style = MBTypography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$$price",
                    color = priceColor.value,
                    style = MBTypography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Name and Ratio
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stockName,
                    color = MaterialTheme.colorScheme.primaryFixed,
                    style = MBTypography.bodySmall
                )

                // Ratio
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    if (!isSame) {
                        Icon(
                            imageVector = trendIcon,
                            contentDescription = null,
                            tint = trendColor,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Text(
                        text = "$prefix$ratio%",
                        color = trendColor,
                        style = MBTypography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StockCardPreview() {
    MultiBankTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Positive
            StockCard(
                symbol = "THYAO",
                price = "285.50",
                stockName = "Turk Hava Yollari",
                ratio = "2.45",
                isPositive = true,
            )

            // Negative
            StockCard(
                symbol = "ASELS",
                price = "54.20",
                stockName = "Aselsan Elektronik",
                ratio = "1.12",
                isPositive = false,
            )

            // Same
            StockCard(
                symbol = "BTC",
                price = "65,000",
                stockName = "Bitcoin",
                ratio = "0.00",
                isPositive = true,
                isSame = true
            )
        }
    }
}