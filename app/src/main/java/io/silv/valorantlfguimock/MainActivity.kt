package io.silv.valorantlfguimock

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.OffsetEffect
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import io.silv.valorantlfguimock.ui.screens.App
import io.silv.valorantlfguimock.ui.screens.ThemeStateHolder
import io.silv.valorantlfguimock.ui.theme.CustomColors
import io.silv.valorantlfguimock.ui.theme.LocalCustomColors
import io.silv.valorantlfguimock.ui.theme.Theme
import io.silv.valorantlfguimock.ui.theme.ValorantLfgUiMockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        setContent {
            val themeStateHolder: ThemeStateHolder = viewModel()

            ValorantLfgUiMockTheme(
                currentTheme = themeStateHolder.theme.value,
            ) {
                App()
            }
        }
    }
}

