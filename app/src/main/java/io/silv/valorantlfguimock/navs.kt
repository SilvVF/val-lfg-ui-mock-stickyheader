package io.silv.valorantlfguimock

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.silv.valorantlfguimock.fixtures.NOTES
import io.silv.valorantlfguimock.ui.atoms.Container
import io.silv.valorantlfguimock.ui.components.*
import io.silv.valorantlfguimock.ui.theme.LocalCustomColors
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home(paddingValues: PaddingValues) {

    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    val items by remember {
        mutableStateOf(NOTES)
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
                    Scaffold {paddingValues ->

                        StickyHeaderColumn(
                            headerBar = {
                               HeaderBar()
                            },
                            modifier = Modifier
                                .fillMaxSize(),
                            scrollStateListener = { scrollState ->

                            }
                        ) {
                            items.forEach {
                                NoteListItem(note = it)
                            }
                        }
                    }


                }
            }
        }
    }
}

@Composable
fun StickyHeaderColumn(
    modifier: Modifier = Modifier,
    headerBarHeight: Float = 50f,
    scrollStateListener: (ScrollState) -> Unit = { },
    animationSpec: AnimationSpec<Float> = SpringSpec(),
    headerBar: @Composable BoxScope.() -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    val ANCHOR_INIT = -10f
    val minY = -headerBarHeight + -5f
    val maxY = headerBarHeight
    val (anchorY, setAnchorY) = remember { mutableStateOf(ANCHOR_INIT) }
    val translationY = remember { Animatable(0f) }
    var progressY by remember { mutableStateOf(0f) }

    LaunchedEffect(!scrollState.isScrollInProgress) {
        Log.d("bar", translationY.toString())
        if(progressY > 0.5f || scrollState.value.toFloat() < headerBarHeight) {
            coroutineScope.launch {
                translationY.animateTo(maxY, animationSpec)
            }
        } else {
            coroutineScope.launch {
                translationY.animateTo(minY, animationSpec)
            }
        }
    }

    LaunchedEffect(key1 = scrollState.value) {
        val offsetY = scrollState.value.toFloat()
        var distY = offsetY - anchorY
        if (anchorY== ANCHOR_INIT) distY = offsetY
        val value = maxOf(minY, minOf(maxY, translationY.value - distY))
        translationY.snapTo(value)
        setAnchorY(offsetY)
        progressY = lerp(minY.dp, maxY.dp, translationY.value).value
    }

    LaunchedEffect(key1 = scrollState) {
        scrollStateListener(scrollState)
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState, true),
        ) {
            content()
        }
        Box(Modifier.offset(y = translationY.asState().value.dp)) {
            headerBar()
        }
    }
}

@Composable
fun HeaderBar() {
    val colors = LocalCustomColors.current
    Box(
        Modifier
            .shadow(
                10.dp, RoundedCornerShape(
                    topEnd = 37.dp,
                    topStart = 50.dp,
                    bottomEnd = 37.dp,
                    bottomStart = 45.dp
                )
            )
            .padding(1.dp)
            .height(50.dp)
            .fillMaxWidth(0.8f)
            .clip(RoundedCornerShape(40.dp))
            .background(colors.headerBarBackground),
        contentAlignment = Alignment.Center
    ) {
        HeaderContent(
            textChange = {},
            onIconClicked = {}
        )
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