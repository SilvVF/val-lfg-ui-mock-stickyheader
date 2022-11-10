package io.silv.valorantlfguimock.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun HeaderContent(
    modifier: Modifier = Modifier,
    textChange: (String) -> Unit,
    onIconClicked: (String) -> Unit
) {
    var searchText by remember {
        mutableStateOf("")
    }
    Row(
        modifier = modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.width(18.dp))
        Box(modifier = Modifier.clip(CircleShape).clickable { onIconClicked(searchText) }) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier
                    .height(22.dp)
                    .width(22.dp)
            )
        }
        OutlinedTextField(
            value = searchText,
            interactionSource = MutableInteractionSource(),
            onValueChange = { searchText = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor= Color.Transparent,
                unfocusedBorderColor= Color.Transparent,
                cursorColor = Color.Black
            ),
            placeholder = {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = "Search...", textAlign = TextAlign.Center, modifier = Modifier.offset(y = -(2).dp))
                }
            },
            textStyle = TextStyle(
                fontWeight = FontWeight.SemiBold,
            ),
            modifier = Modifier.fillMaxWidth(0.85f)
        )
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Search",
            modifier = Modifier
                .height(22.dp)
                .width(22.dp)
                .clickable {

                }
        )
        Spacer(modifier = Modifier.width(18.dp))
    }
}