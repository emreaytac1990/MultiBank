package com.emreaytac.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.emreaytac.designsystem.theme.LocalExtendedColors

@Composable
fun StockDetailShimmer() {
    val brush = shimmerBrush()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(brush)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .size(width = 100.dp, height = 20.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(brush)
            )
        }

        Box(
            modifier = Modifier
                .size(width = 140.dp, height = 40.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(brush)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .size(width = 200.dp, height = 18.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(brush)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, LocalExtendedColors.current.cardBorder, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {

                Box(modifier = Modifier.size(width = 110.dp, height = 14.dp).clip(RoundedCornerShape(4.dp)).background(brush))
                Spacer(modifier = Modifier.height(12.dp))

                Box(modifier = Modifier.size(width = 160.dp, height = 36.dp).clip(RoundedCornerShape(4.dp)).background(brush))
                Spacer(modifier = Modifier.height(12.dp))

                Box(modifier = Modifier.size(width = 70.dp, height = 16.dp).clip(RoundedCornerShape(4.dp)).background(brush))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, LocalExtendedColors.current.cardBorder, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {

                Box(modifier = Modifier.size(width = 80.dp, height = 18.dp).clip(RoundedCornerShape(4.dp)).background(brush))
                Spacer(modifier = Modifier.height(12.dp))

                Box(modifier = Modifier.fillMaxWidth().height(14.dp).clip(RoundedCornerShape(4.dp)).background(brush))
                Spacer(modifier = Modifier.height(8.dp))
                Box(modifier = Modifier.fillMaxWidth(0.9f).height(14.dp).clip(RoundedCornerShape(4.dp)).background(brush))
                Spacer(modifier = Modifier.height(8.dp))
                Box(modifier = Modifier.fillMaxWidth(0.7f).height(14.dp).clip(RoundedCornerShape(4.dp)).background(brush))
            }
        }
    }
}