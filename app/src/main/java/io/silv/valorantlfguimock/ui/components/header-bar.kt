package io.silv.valorantlfguimock.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.silv.valorantlfguimock.ui.theme.LocalCustomColors

@Composable
fun HeaderBar(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    visible: Boolean = true,
    content: @Composable BoxScope.() -> Unit = {}
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(paddingValues.calculateTopPadding())
                .background(Color.Transparent),
            contentAlignment = Alignment.TopCenter
        ) {
            val colors = LocalCustomColors.current
            Box(
                modifier
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
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(40.dp))
                    .background(colors.headerBarBackground),
                contentAlignment = Alignment.Center
            ) {
                HeaderContent(
                    textChange = {},
                    onIconClicked = {}
                )
                content()
            }
        }
    }
}
