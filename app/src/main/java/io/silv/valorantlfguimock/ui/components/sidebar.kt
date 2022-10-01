package io.silv.valorantlfguimock.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.silv.valorantlfguimock.ui.atoms.Container
import io.silv.valorantlfguimock.ui.theme.*

data class SideBarContentProps(
    val children: @Composable () -> Unit = { },
    val modifier: Modifier = Modifier,
    val paddingValues: PaddingValues = PaddingValues()
)


@Composable
fun Sidebar(
    props: SideBarContentProps = SideBarContentProps()
) {
    Container(
        Modifier
            .fillMaxSize(),
        Alignment.Center
    ) {
        Column(
            modifier = props.modifier
                .fillMaxSize()
                .padding(props.paddingValues.calculateTopPadding()),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Valorant LFG",
                fontSize = 18.sp,
                color = slate100
            )
        }
    }
}