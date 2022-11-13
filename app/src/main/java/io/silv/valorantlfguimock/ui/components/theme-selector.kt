package io.silv.valorantlfguimock.ui.components


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.silv.valorantlfguimock.ui.screens.ThemeStateHolder
import io.silv.valorantlfguimock.ui.theme.Theme
import io.silv.valorantlfguimock.ui.theme.blue70

@Composable
fun ThemeSelector(
    modifier: Modifier = Modifier,
    themeStateHolder: ThemeStateHolder = viewModel()
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val theme = themeStateHolder.theme.value

        ThemeItem(
            modifier = Modifier.fillMaxWidth(),
            name ="light",
            selected = derivedStateOf {
            theme == Theme.Light
        }.value) {
            themeStateHolder.changeThemeHandler(Theme.Light)
        }
        ThemeItem(
            modifier = Modifier.fillMaxWidth(),
            name ="dark",
            selected = derivedStateOf {
            theme == Theme.Dark
        }.value) {
            themeStateHolder.changeThemeHandler(Theme.Dark)
        }
    }
}

@Composable
fun ThemeItem(
    modifier: Modifier = Modifier,
    name: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Row(modifier.clickable {
        onClick()
    }.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(25.dp)) {

       val r = if (selected) animateFloatAsState(targetValue = 20f) .value
       else animateFloatAsState(targetValue = 0f).value
       Canvas(
           modifier = Modifier.height(30.dp).padding(start = 25.dp).offset(y = 2.dp),
       ) {

           drawCircle(
               blue70,
               radius = r
           )
       }
       Text(
           text = name,
           fontSize = 28.sp,
           modifier = Modifier,
           color = if (selected) animateColorAsState(targetValue = Color.LightGray).value
            else animateColorAsState(targetValue = Color.DarkGray).value,
       )
    }
}