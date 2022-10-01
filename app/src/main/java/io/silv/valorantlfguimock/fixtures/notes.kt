package io.silv.valorantlfguimock.fixtures

import android.util.Log
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import io.silv.valorantlfguimock.Note
import java.util.UUID
import kotlin.random.Random

private val DATA: List<Note>
    get() = createLorem()

private fun lorem(count: Int) = LoremIpsum(count).values

private fun capitalizeFirstLetter(s: String) = s.replace(s.first(), s.first().uppercaseChar()) + if (s.length > 1) s.drop(1) else ""

private fun getRandInt(s: Int, e: Int) = (s..e).random()

private fun createLorem(): List<Note> {

    return (0..100).map {
        Note(
            id = UUID.randomUUID().toString(),
            title = capitalizeFirstLetter(lorem(getRandInt(1,10)).joinToString("")),
            body = capitalizeFirstLetter(lorem(getRandInt(8,40)).joinToString(""))
        )
    }
}

val NOTES = DATA.toList()
