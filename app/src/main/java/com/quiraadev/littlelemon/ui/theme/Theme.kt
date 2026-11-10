package com.quiraadev.littlelemon.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Nandor,
    secondary = GoldenCorn,
    tertiary = DarkApricot,
    background = DarkCharcoal,
    surface = DarkCharcoal,
    onPrimary = Color.White,
    onSecondary = DarkCharcoal,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Nandor,
    secondary = GoldenCorn,
    tertiary = DarkApricot,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = DarkCharcoal,
    onBackground = DarkCharcoal,
    onSurface = DarkCharcoal
)

@Composable
fun LittleLemonTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}