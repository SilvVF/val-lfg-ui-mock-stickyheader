package io.silv.valorantlfguimock.ui.screens

import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.silv.valorantlfguimock.ui.theme.Theme

class ThemeStateHolder: ViewModel() {

    var theme: MutableState<Theme> = mutableStateOf(Theme.Light)
        private set

    fun changeThemeHandler(newTheme: Theme) {
        theme.value = newTheme
    }
}