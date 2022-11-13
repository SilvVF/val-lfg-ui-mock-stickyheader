package io.silv.valorantlfguimock.ui.screens

import android.content.res.Resources
import android.util.Log
import android.util.TypedValue
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.lifecycle.viewmodel.compose.viewModel
import io.silv.valorantlfguimock.Navigation
import io.silv.valorantlfguimock.R
import io.silv.valorantlfguimock.ui.components.ThemeSelector
import io.silv.valorantlfguimock.ui.theme.LocalCustomColors
import io.silv.valorantlfguimock.ui.theme.Theme
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App() {

    val themeStateHolder = viewModel<ThemeStateHolder>()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.Expanded)
    )

    BottomSheetScaffold(
        sheetContent = {
            AnimatedSheetContent(sheetState =bottomSheetScaffoldState.bottomSheetState)
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 90.dp
    ) {
        Navigation()
    }
}
val Number.toPx get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics)

@OptIn(ExperimentalMaterialApi::class, ExperimentalMotionApi::class)
@Composable
fun AnimatedSheetContent(
    sheetState: BottomSheetState
) {
    val (isClosing, setIsClosing )= remember {
        mutableStateOf(false)
    }
    val sheetMaxSize = 2092.5f
    LaunchedEffect(key1 = sheetState.isExpanded, sheetState.isCollapsed) {
        setIsClosing(sheetState.isExpanded)
    }

    val sheetProgress = remember(sheetState.offset) {
        //derivedStateOf { sheetMaxSize / sheetState.offset.value }
        derivedStateOf {
            //val progress = (sheetState.progress.fraction) / 0.3f
            when {
                /*progress is always from -> to
                  if the sheet is closing subtract 1 to get the current
                  progress from the start of it being closed */
                //PCT = Y / X
                isClosing -> ((sheetState.offset.value - 1240) / (2092.5 - 1240))
                else -> 1 - ((sheetState.offset.value - 1240) / (2092.5 - 1240))
//                isClosing -> (1 - progress * 0.5f).takeIf { it < 0.4f }?.coerceIn(0f, 1f) ?: 1f
//                else -> progress.coerceIn(0f , 1f)
            }
        }
    }.value
    val context = LocalContext.current
    val scene = remember {
        context.resources.openRawResource(R.raw.animated_bottom_sheet)
            .readBytes()
            .decodeToString()
    }

    LaunchedEffect(key1 = sheetProgress, key2 = sheetState.direction,block = { Log.d("sheet","$sheetProgress :: ${sheetState.offset.value}")} )

    Column(modifier = Modifier
        .fillMaxWidth()
        .height(400.dp)
    ) {
        MotionLayout(
            motionScene = MotionScene(content = scene),
            progress = sheetProgress.toFloat().coerceIn(0f, 1f),
            modifier = Modifier.fillMaxSize()
        ) {
            val props = motionProperties(id = "title_text")
            val fontSize = props.value.int("fontSize")

            Text(text = "Login or sign up", modifier = Modifier
                .layoutId("title_text")
                .fillMaxWidth(),
                fontSize = fontSize.sp
            )
            Text(text = "Sign up to sync offline", modifier = Modifier
                .layoutId("description_text")
                .fillMaxWidth(),
                fontSize = 14.sp
            )
        }
    }
}