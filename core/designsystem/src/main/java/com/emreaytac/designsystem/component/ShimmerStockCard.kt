package com.emreaytac.designsystem.component

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.emreaytac.designsystem.theme.LocalExtendedColors


@Composable
fun StockCardShimmer(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 12.dp,
) {
    val brush = shimmerBrush()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(cornerRadius))
            .background( MaterialTheme.colorScheme.surface)
            .border(1.dp, LocalExtendedColors.current.cardBorder, RoundedCornerShape(cornerRadius))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Box(modifier = Modifier.size(width = 80.dp, height = 20.dp).clip(RoundedCornerShape(4.dp)).background(brush))

                Box(modifier = Modifier.size(width = 60.dp, height = 20.dp).clip(RoundedCornerShape(4.dp)).background(brush))
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Box(modifier = Modifier.size(width = 120.dp, height = 16.dp).clip(RoundedCornerShape(4.dp)).background(brush))

                Box(modifier = Modifier.size(width = 50.dp, height = 16.dp).clip(RoundedCornerShape(4.dp)).background(brush))
            }
        }
    }
}
