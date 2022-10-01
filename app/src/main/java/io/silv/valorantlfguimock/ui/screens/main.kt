package io.silv.valorantlfguimock.ui.screens


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import io.silv.valorantlfguimock.Note
import io.silv.valorantlfguimock.fixtures.NOTES
import io.silv.valorantlfguimock.ui.atoms.Container
import io.silv.valorantlfguimock.ui.components.NoteList

@Composable
fun MainScreen(
    paddingValues: PaddingValues,
    listState: LazyListState,
    items: List<Note> = NOTES
) {
    Container(
        contentAlignment = Alignment.Center
    ) {
        NoteList(lazyListState = listState, items = items)
    }
}