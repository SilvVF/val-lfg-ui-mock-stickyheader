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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.OffsetEffect
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import io.silv.valorantlfguimock.ui.theme.CustomColors
import io.silv.valorantlfguimock.ui.theme.LocalCustomColors
import io.silv.valorantlfguimock.ui.theme.ValorantLfgUiMockTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContent {
            ValorantLfgUiMockTheme {
                BottomSheetScaffold(
                    sheetContent = {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                            .background(LocalCustomColors.current.sidebarBackground)
                        ) {
                            Text(
                                text = "Dark",
                                Modifier.clickable {

                                }
                            )
                        }
                    },
                    sheetPeekHeight = 45.dp
                ) {
                    Navigation()
                }
            }
        }
    }
}

