package io.silv.valorantlfguimock.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import io.silv.valorantlfguimock.Note
import io.silv.valorantlfguimock.fixtures.NOTES


@Composable
fun NoteList(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    items: List<Note> = NOTES
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = lazyListState
    ) {
        items(
            items = items,
            key = { note -> note.id }
        ) { item ->
            NoteListItem(note = item)
        }
    }
}
