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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


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
    val maxY = 50f
    val anchorY = remember { mutableStateOf(ANCHOR_INIT) }
    var translationY = remember { Animatable(0f) }
    var progressY by remember { mutableStateOf(0f) }
    var anim by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
   LaunchedEffect(!scrollState.isScrollInProgress) {
       Log.d("bar", translationY.toString())
       if(progressY > 0.5f || scrollState.value.toFloat() < headerBarHeight) {
           Log.d("bar", "$translationY trans")
           Log.d("bar", "$maxY tmax")
           coroutineScope.launch {
               translationY.animateTo(maxY)
           }
        } else {
           Log.d("bar", "$translationY trans")
           Log.d("bar", "$minY tmax")
           coroutineScope.launch {
               translationY.animateTo(minY)
           }
        }
    }
    LaunchedEffect(key1 = scrollState.value) {
        val offsetY = scrollState.value.toFloat()
        var distY = offsetY - anchorY.value
        if (anchorY.value == ANCHOR_INIT) distY = offsetY
        val value = maxOf(minY, minOf(maxY, translationY.value - distY))
        translationY.snapTo(value)
        anchorY.value = offsetY
        progressY = lerp(minY.dp, maxY.dp, translationY.value).value
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
                                offset = translationY.asState().value
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