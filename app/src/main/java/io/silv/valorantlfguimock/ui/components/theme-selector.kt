package io.silv.valorantlfguimock.ui.components

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.silv.valorantlfguimock.ui.theme.Theme
import io.silv.valorantlfguimock.ui.theme.paper300

@Composable
fun ThemeSelector(
    themeName: String,
    containerModifier: Modifier = Modifier,
    theme: Theme = Theme.Light,
    highlight: Color,
    selected: Boolean,
    onClick: () -> Unit
) {

    var backgroundSize by remember {
        mutableStateOf(
            androidx.compose.animation.core.Animatable(0f)
        )
    }

    val interactionSource = remember {
        MutableInteractionSource()
    }
    LaunchedEffect(key1 = selected) {
        if (selected) {
            backgroundSize.animateTo(0.8f)
        } else {
            backgroundSize.animateTo(0f)
        }
    }

    Box(
        modifier = containerModifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Filled.LightMode.apply {

                },
                tint = if (selected) animateColorAsState(targetValue = Color.White).value
                else animateColorAsState(targetValue = Color.LightGray).value,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(50.dp))
            Text(
                text = themeName,
                color = if (selected) 
                    animateColorAsState(targetValue = Color.White).value
                else animateColorAsState(targetValue = Color.LightGray).value,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                fontSize = 22.sp,
                modifier = Modifier.offset(y = -(2.dp))
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(backgroundSize.value)
                .fillMaxHeight(0.8f)
                .clip(RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp))
                .background(highlight.copy(alpha = 0.25f))
        )
    }
}