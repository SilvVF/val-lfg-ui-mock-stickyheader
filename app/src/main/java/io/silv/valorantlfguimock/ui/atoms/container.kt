package io.silv.valorantlfguimock.ui.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.silv.valorantlfguimock.ui.theme.LocalCustomColors

@Composable
fun Container(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment,
    propagateMinConstraints: Boolean = false,
    content: @Composable() (BoxScope.() -> Unit) = { }
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LocalCustomColors.current.background),
        contentAlignment = contentAlignment,
        propagateMinConstraints,
    ) {
        content()
    }
}