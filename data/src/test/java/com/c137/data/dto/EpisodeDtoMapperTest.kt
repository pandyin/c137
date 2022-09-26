package com.c137.data.dto

import android.net.Uri
import com.c137.data.model.dto.EpisodeDto
import com.c137.data.model.mapper.dto.AIR_DATE_PATTERN
import com.c137.data.model.mapper.dto.toDataModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.text.SimpleDateFormat
import java.util.UUID

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [32])
class EpisodeDtoMapperTest {

    @Test
    fun `should map a character dto to a data character correctly`() {
        val dto = EpisodeDto(
            id = UUID.randomUUID().hashCode(),
            name = UUID.randomUUID().toString(),
            air_date = "December 16, 1988",
            episode = UUID.randomUUID().toString(),
            characters = listOf(
                "https://rickandmortyapi.com/api/character/1",
                "https://rickandmortyapi.com/api/character/2"
            )
        )

        val dataModel = dto.toDataModel()

        assertEquals(dto.id, dataModel.id)
        assertEquals(dto.name, dataModel.name)
        assertEquals(
            SimpleDateFormat(AIR_DATE_PATTERN).parse(dto.air_date)!!.time,
            dataModel.airDate
        )
        assertEquals(dto.episode, dataModel.episode)
        dto.characters.forEachIndexed { index, resident ->
            assertEquals(
                Uri.parse(resident).lastPathSegment?.toIntOrNull(),
                dataModel.characters[index]
            )
        }
    }
}