package io.silv.valorantlfguimock.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val slate00= Color(0xFF1b1c1d)
val slate10= Color(0xFF202225)
val slate20= Color(0xFF292c2f)
val slate30= Color(0xFF2e3235)
val slate40= Color(0xFF35393d)
val slate100=Color( 0xFF767577)
val slate900= Color(0xFFdddddd)
val blue70= Color(0xFF2185d0)

val paper00=Color(0xffffffff)
val paper10=Color(0xfff5f5f4)
val paper20= Color(0xffe6e6e6)
val paper300 = Color(0xff767577)
val paper900 = Color(0xff202020)
val navy20 = Color(0xff171a21)
val navy900 = Color(0xffb9babc)

@Immutable
data class CustomColorsLight (
    override val primary: Color = Color(0xff2185d0),
    override val windowBackground: Color = Color(0xfff0f0f0),
    override val background: Color = Color(0xfff5f5f4),
    override val foreground: Color = paper900,
    override val sidebarBackground: Color = navy20,
    override val sidebarForeground: Color = navy900,
    override val sidebarSeparator: Color = paper00,
    override val headerBarBackground: Color = paper20,
) : CustomColors

@Immutable
data class CustomColorsDark  (
    override val primary: Color = Color.Red,
    override val windowBackground: Color =  Color.Red,
    override val background: Color =  Color.Red,
    override val foreground: Color =  Color.Red,
    override val sidebarBackground: Color = navy20,
    override val sidebarForeground: Color =Color.DarkGray,
    override val sidebarSeparator: Color = Color.Red,
    override val headerBarBackground: Color = Color.Red,
) : CustomColors