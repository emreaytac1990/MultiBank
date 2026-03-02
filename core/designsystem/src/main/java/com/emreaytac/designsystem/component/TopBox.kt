package com.emreaytac.designsystem.component

import android.R.attr.text
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.emreaytac.designsystem.theme.MBTypography
import com.emreaytac.designsystem.theme.MultiBankTheme

@Composable
fun TopBox(
    cornerRadius: Dp = 8.dp,
    borderColor: Color = Color.Gray,
    mainColor: Color = Color.Blue,
    backgroundColor: Color = Color.White,
    isCircle: Boolean = true,
    text: String = "",
    width: Dp? = null,
    height: Dp = 80.dp,
    isClickable: Boolean = true,
    showRipple: Boolean = true,
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .padding(1.dp)
            .then(if (width != null) Modifier.width(width) else Modifier.wrapContentWidth())
            .height(height)
            .clip(RoundedCornerShape(cornerRadius))
            .then(
                if (isClickable) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = if (showRipple) LocalIndication.current else null,
                        onClick = onClick
                    )
                } else Modifier
            )
            .background(color = backgroundColor, shape = RoundedCornerShape(cornerRadius))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(cornerRadius))
            .padding(vertical = 7.dp, horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Box(
                modifier = Modifier
                    .size(7.dp)
                    .clip(if (isCircle) CircleShape else RoundedCornerShape(0.dp))
                    .background(mainColor)
            )

            Spacer(modifier = Modifier.width(12.dp))


            Text(
                text = text,
                color = mainColor,
                style = MBTypography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true, name = "Green")
@Composable
fun PreviewBlueCircle() {
    MultiBankTheme {
        TopBox(
            cornerRadius = 12.dp,
            mainColor = Color(0xFF059669),
            borderColor = Color(0xFF00E5A0),
            backgroundColor = Color(0xFFE5F2F0),
            isCircle = true,
            text = "LIVE",
            width = 72.dp,
            height = 32.dp
        )
    }
}

@Preview(showBackground = true, name = "Red")
@Composable
fun PreviewGreenSquare() {
    MultiBankTheme {
        TopBox(
            cornerRadius = 12.dp,
            mainColor = Color(0xFFDC2626),
            borderColor = Color(0xFFF6B9BA),
            backgroundColor = Color(0xFFF8EBED),
            isCircle = false,
            text = "STOP FEED",
            width = 118.dp,
            height = 38.dp
        )
    }
}