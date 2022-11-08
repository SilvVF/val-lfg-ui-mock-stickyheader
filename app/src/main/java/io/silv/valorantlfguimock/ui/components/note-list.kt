package io.silv.valorantlfguimock.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import io.silv.valorantlfguimock.Note
import io.silv.valorantlfguimock.fixtures.NOTES


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteList(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    items: List<Note> = NOTES
) {
   Column(
        modifier = modifier.fillMaxWidth().verticalScroll(scrollState, true),
    ) {
        items.forEach{   item ->
            NoteListItem(note = item , )
        }
    }
}
