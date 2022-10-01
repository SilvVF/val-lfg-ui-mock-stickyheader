package io.silv.valorantlfguimock.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorScheme = darkColors(
    primary = blue70,
    secondary = slate00,
    background = slate10,
    surface = slate900,
)

@Immutable
data class CustomColors(
    val primary: Color = Color(0xff2185d0),
    val windowBackground: Color = Color(0xfff0f0f0),
    val background: Color = Color(0xfff5f5f4),
    val foreground: Color = paper900,
    val sidebarBackground: Color = navy20,
    val sidebarForeground: Color = navy900,
    val sidebarSeparator: Color = paper00,
    val headerBarBackground: Color = paper20,
)

val customColors = CustomColors()

val LocalCustomColors = staticCompositionLocalOf {
    CustomColors()
}

private val LightColorScheme = lightColors(
    primary = Purple40,
    secondary = PurpleGrey40,

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ValorantLfgUiMockTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) darkColors() else lightColors()
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }
    CompositionLocalProvider(LocalCustomColors.providesDefault(CustomColors())) {
        MaterialTheme(
            colors = colorScheme,
            typography = Typography,
            content = content
        )
    }
}