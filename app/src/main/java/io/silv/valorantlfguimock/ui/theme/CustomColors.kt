package io.silv.valorantlfguimock.ui.theme

import androidx.compose.ui.graphics.Color

interface CustomColors {
    val primary: Color
    val windowBackground: Color
    val background: Color
    val foreground: Color
    val sidebarBackground: Color
    val sidebarForeground: Color
    val sidebarSeparator: Color
    val headerBarBackground: Color
}


sealed interface Theme{
    object Light: Theme
    object Dark: Theme
    object Nord: Theme
    object Solarized: Theme
}