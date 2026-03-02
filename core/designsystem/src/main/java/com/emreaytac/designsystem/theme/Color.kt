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

internal val cardLight            = Color(0xFF0F172A)
internal val cardDark             = Color(0xFFF0F2F7)

internal val cardBorderLight      = Color(0x0F000000)
internal val cardBorderDark       = Color(0x12FFFFFF)

internal val topBoxBorderPositiveLight      = Color(0x40059669)
internal val topBoxBorderPositiveDark       = Color(0x40059669)

internal val topBoxBorderNegativeLight      = Color(0xFFF6B9BA)
internal val topBoxBorderNegativeDark       = Color(0xFFF6B9BA)

internal val topBoxBgPositiveLight      = Color(0xFFE5F2F0)
internal val topBoxBgPositiveDark       = Color(0xFFE5F2F0)

internal val topBoxBgNegativeLight      = Color(0xFFF8EBED)
internal val topBoxBgNegativeDark       = Color(0xFFF8EBED)






data class MBExtendedColors(
    val gold: Color,
    val goldGlow: Color,
    val cardMainText: Color,
    val cardBorder: Color,
    val topBoxBorderPositive: Color,
    val topBoxBorderNegative: Color,
    val topBoxBgPositive: Color,
    val topBoxBgNegative: Color
)



val LightExtendedColors = MBExtendedColors(
    gold                    = goldLight,
    goldGlow                = goldLight.copy(alpha = 0.5f),
    cardMainText            = cardLight,
    cardBorder              = cardBorderLight,
    topBoxBorderPositive    = topBoxBorderPositiveLight,
    topBoxBorderNegative    = topBoxBorderNegativeLight,
    topBoxBgPositive        = topBoxBgPositiveLight,
    topBoxBgNegative        = topBoxBgNegativeLight
)

val DarkExtendedColors = MBExtendedColors(
    gold                    = goldDark,
    goldGlow                = goldDark.copy(alpha = 0.4f),
    cardMainText            = cardDark,
    cardBorder              = cardBorderDark,
    topBoxBorderPositive    = topBoxBorderPositiveDark,
    topBoxBorderNegative    = topBoxBorderNegativeDark,
    topBoxBgPositive        = topBoxBgPositiveDark,
    topBoxBgNegative        = topBoxBgNegativeDark
)

val LocalExtendedColors = staticCompositionLocalOf { LightExtendedColors }