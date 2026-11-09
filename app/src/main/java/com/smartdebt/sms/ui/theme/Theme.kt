package com.smartdebt.sms.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Palette from the project spec (section 25).
val FinanceDarkBlue = Color(0xFF103B59)
val Blue = Color(0xFF087FA8)
val Turquoise = Color(0xFF00D5D9)
val AppBackground = Color(0xFFF2F5FA)
val MainText = Color(0xFF252525)
val SecondaryText = Color(0xFF738096)
val PaidGreen = Color(0xFF188754)
val DebtRed = Color(0xFFC93636)
val AlertYellow = Color(0xFFF2C94C)
val WarningOrange = Color(0xFFE58A16)
val BorderGray = Color(0xFFE5E7EB)

private val LightColors = lightColorScheme(
    primary = FinanceDarkBlue,
    secondary = Blue,
    tertiary = Turquoise,
    background = AppBackground,
    surface = Color.White,
    onPrimary = Color.White,
    onBackground = MainText,
    onSurface = MainText,
    onSurfaceVariant = SecondaryText,
    outline = BorderGray,
    error = DebtRed
)

private val DarkColors = darkColorScheme(
    primary = Turquoise,
    secondary = Blue,
    tertiary = Turquoise,
    background = Color(0xFF0B1F2E),
    surface = Color(0xFF12293B),
    onPrimary = FinanceDarkBlue,
    onBackground = Color(0xFFEAF0F6),
    onSurface = Color(0xFFEAF0F6),
    onSurfaceVariant = Color(0xFF9FB0C3),
    outline = Color(0xFF2A4258),
    error = Color(0xFFFF8A8A)
)

@Composable
fun SmartDebtTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) DarkColors else LightColors,
        content = content
    )
}
