package com.c137.data.dto

import android.net.Uri
import com.c137.data.model.dto.LocationDto
import com.c137.data.model.mapper.dto.toDataModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.UUID

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [32])
class LocationDtoMapperTest {

    @Test
    fun `should map a location dto to a data location correctly`() {
        val dto = LocationDto(
            id = UUID.randomUUID().hashCode(),
            name = UUID.randomUUID().toString(),
            type = UUID.randomUUID().toString(),
            dimension = UUID.randomUUID().toString(),
            residents = listOf(
                "https://rickandmortyapi.com/api/character/1",
                "https://rickandmortyapi.com/api/character/2"
            )
        )

        val dataModel = dto.toDataModel()

        assertEquals(dto.id, dataModel.id)
        assertEquals(dto.name, dataModel.name)
        assertEquals(dto.type, dataModel.type)
        assertEquals(dto.dimension, dataModel.dimension)
        dto.residents.forEachIndexed { index, resident ->
            assertEquals(
                Uri.parse(resident).lastPathSegment?.toIntOrNull(),
                dataModel.residents[index]
            )
        }
    }
}