package io.silv.valorantlfguimock

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.silv.valorantlfguimock.fixtures.NOTES
import io.silv.valorantlfguimock.ui.atoms.Container
import io.silv.valorantlfguimock.ui.components.*
import io.silv.valorantlfguimock.ui.screens.MainScreen
import io.silv.valorantlfguimock.ui.theme.LocalCustomColors


@Composable
fun Home(paddingValues: PaddingValues) {

    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    val items by remember {
        mutableStateOf(NOTES)
    }

    val ANCHOR_INIT = 0f
    val (headerBarHeight, setHeaderBarHeight) = remember { mutableStateOf(50f) }
    val minY = -headerBarHeight
    val maxY = 40f
    val anchorY = remember {
        mutableStateOf(ANCHOR_INIT)
    }
    var translationY by remember {
        mutableStateOf(0f)
    }
    var progressY by remember {
        mutableStateOf(0f)
    }


    val interaction = scrollState.interactionSource.interactions.collectAsState(DragInteraction.Stop(DragInteraction.Start()))
    if (interaction.value is DragInteraction.Stop || interaction.value is DragInteraction.Cancel) {
        if(progressY > 0.5f || scrollState.value.toFloat() < headerBarHeight) {
            Log.d("bar progY", "herer")
            translationY = animateFloatAsState(
                targetValue = maxY,
                animationSpec = tween(delayMillis = 10000,easing = LinearOutSlowInEasing)
            ).value
        } else {
            Log.d("bar progY", "hesdfasdfrer")
            translationY = animateFloatAsState(
                targetValue = minY,
                animationSpec = tween(delayMillis = 10000,easing = LinearOutSlowInEasing)
            ).value
        }
    }
    LaunchedEffect(key1 = scrollState.value) {
        val offsetY = scrollState.value.toFloat()
        var distY = offsetY - anchorY.value
        if (anchorY.value == ANCHOR_INIT) distY = offsetY
        val value = if (offsetY <= -40f) maxY else maxOf(minY, minOf(maxY, translationY - distY))
        translationY = value
        anchorY.value = offsetY
        progressY = lerp(minY.dp, maxY.dp, translationY).value
        Log.d("bar ProgressY", translationY.toString())
    }

    ModalDrawer(
        drawerContent = {
            Sidebar(SideBarContentProps(paddingValues = paddingValues))
        },
        drawerBackgroundColor = LocalCustomColors.current.sidebarBackground
    ) {
        Container(Modifier.fillMaxSize(), Alignment.Center) {
            NavHost(navController = navController, startDestination = "MainScreen") {
                composable("MainScreen") {
                    Scaffold { paddingValues ->
                        Box(Modifier.fillMaxSize()) {
                            MainScreen(
                                paddingValues = paddingValues,
                                scrollState = scrollState,
                                items = items,
                            )
                            HeaderBar(
                                paddingValues = paddingValues,
                                offset = translationY
                            )
                        }
                    }
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Navigation() {
    val snackBarHostState: SnackbarHostState = remember {
        SnackbarHostState()
    }
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) {
        it.calculateTopPadding()
        val padding = WindowInsets(top = 40.dp).asPaddingValues()
        NavHost(
            navController = navController,
            startDestination = "Home"
        ) {
            composable("Home") {
                    Home(padding)
            }
        }
    }
}