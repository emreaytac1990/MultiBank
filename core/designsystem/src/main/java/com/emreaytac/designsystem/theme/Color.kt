package com.emreaytac.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


internal val backgroundLight      = Color(0xFFF8FAFC)
internal val backgroundDark       = Color(0xFF0A0B0F)

internal val surfaceLight         = Color(0xFFFFFFFF)
internal val surfaceDark          = Color(0xFF111318)


internal val primaryLight         = Color(0xFF5B4AF7)
internal val primaryDark          = Color(0xFF8B7EFF)


internal val positiveLight        = Color(0xFF059669)
internal val positiveDark         = Color(0xFF00E5A0)


internal val negativeLight        = Color(0xFFDC2626)
internal val negativeDark         = Color(0xFFFF4D6D)


internal val accentAltLight       = Color(0xFFD97706)
internal val accentAltDark        = Color(0xFFF5C842)


internal val infoLinkLight        = Color(0xFF3B82F6)
internal val infoLinkDark         = Color(0xFF4F8EF7)

internal val goldLight             = Color(0xFFFFB830)
internal val goldDark              = Color(0xFFFFCB5B)


data class MBExtendedColors(
    val gold: Color,
    val goldGlow: Color,
)

val LightExtendedColors = MBExtendedColors(
    gold                    = goldLight,
    goldGlow                = goldLight.copy(alpha = 0.5f)
)

val DarkExtendedColors = MBExtendedColors(
    gold                    = goldDark,
    goldGlow                = goldDark.copy(alpha = 0.4f)
)

val LocalExtendedColors = staticCompositionLocalOf { LightExtendedColors }