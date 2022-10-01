package io.silv.valorantlfguimock.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.silv.valorantlfguimock.Note

@Composable
fun NoteListItem(
    note: Note
) {
    Box {
        Column( modifier = Modifier
            .padding(horizontal = 18.dp, vertical = 8.dp)
        ) {
            Text(
                text = note.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = note.body,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                color = Color.Unspecified.copy(alpha = 0.7f)
            )
        }
    }
}