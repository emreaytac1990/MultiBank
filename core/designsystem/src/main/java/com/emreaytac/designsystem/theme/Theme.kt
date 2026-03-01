package com.emreaytac.designsystem.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

/**
 * Light default theme color scheme
 */
@VisibleForTesting
internal val LightDefaultColorScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = Color.White,
    background = backgroundLight,
    onBackground = Color.Black,
    surface = surfaceLight,
    onSurface = Color.Black,
    secondary = positiveLight,
    error = negativeLight,
    tertiary = accentAltLight,
    tertiaryFixed = infoLinkLight,
)

/**
 * Dark default theme color scheme
 */
@VisibleForTesting
internal val DarkDefaultColorScheme = lightColorScheme(
    primary = primaryDark,
    onPrimary = Color.White,
    background = backgroundDark,
    onBackground = Color.White,
    surface = surfaceDark,
    onSurface = Color.White,
    secondary = positiveDark,
    error = negativeDark,
    tertiary = accentAltDark,
    tertiaryFixed = infoLinkDark,
)


/**
 * MultiBank theme.
 */
@Composable
fun MultiBankTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    androidTheme: Boolean = false,
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit,
){
    // Color scheme
    val colorScheme = when {
        androidTheme -> if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme
        !disableDynamicTheming && supportsDynamicTheming() -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        else -> if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme
    }

    val extendedColors = if (darkTheme) DarkExtendedColors else LightExtendedColors
    // Composition locals
    CompositionLocalProvider(
        LocalExtendedColors provides extendedColors,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = MBTypography,
            content = content,
        )
    }
}


@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
