package io.silv.valorantlfguimock

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.silv.valorantlfguimock.fixtures.NOTES
import io.silv.valorantlfguimock.ui.atoms.Container
import io.silv.valorantlfguimock.ui.components.*
import io.silv.valorantlfguimock.ui.screens.MainScreen
import io.silv.valorantlfguimock.ui.theme.LocalCustomColors
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Home(paddingValues: PaddingValues) {

    val lazyListJobScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val lazyListScrollingUp = lazyListState.isScrollingUp()
    val navController = rememberNavController()
    val items by remember {
        mutableStateOf(NOTES)
    }
    var showHeaderBar by remember {
        mutableStateOf(true)
    }
    var showHeaderBarJob: Job? by remember {
        mutableStateOf(null)
    }

    LaunchedEffect(key1 = lazyListScrollingUp) {
        showHeaderBarJob?.cancel()
        if (lazyListScrollingUp) {
            showHeaderBarJob = lazyListJobScope.launch {
                delay(100)
                showHeaderBar = true
            }
        } else {
            showHeaderBarJob = lazyListJobScope.launch {
                delay(300)
                showHeaderBar = false
            }
        }
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
                    Scaffold {
                        Box(Modifier.fillMaxSize()) {
                            MainScreen(it, lazyListState, items)
                            HeaderBar(paddingValues = paddingValues, visible = showHeaderBar)
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