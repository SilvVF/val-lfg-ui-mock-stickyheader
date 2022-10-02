package io.silv.valorantlfguimock.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RadioButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.silv.valorantlfguimock.Navigation
import io.silv.valorantlfguimock.ui.components.ThemeSelector
import io.silv.valorantlfguimock.ui.theme.LocalCustomColors
import io.silv.valorantlfguimock.ui.theme.Theme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App() {

    val themeStateHolder = viewModel<ThemeStateHolder>()

    BottomSheetScaffold(
        sheetContent = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(LocalCustomColors.current.sidebarBackground)
            ) {
                Column {
                    Spacer(modifier = Modifier.height(45.dp))
                    ThemeSelector(
                        containerModifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .padding(top = 4.dp, bottom = 4.dp),
                        themeName = "Light",
                        selected = themeStateHolder.theme.value is Theme.Light,
                        onClick = {
                            themeStateHolder.changeThemeHandler(Theme.Light)
                        },
                        highlight = Color.Yellow
                    )
                    ThemeSelector(
                        containerModifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .padding(top = 4.dp, bottom = 4.dp),
                        themeName = "Red",
                        selected = themeStateHolder.theme.value is Theme.Dark,
                        onClick = {
                            themeStateHolder.changeThemeHandler(Theme.Dark)
                        },
                        highlight = Color.Red
                    )
                }
            }
        },
        sheetPeekHeight = 45.dp
    ) {
        Navigation()
    }
}