package com.c137.data.dto

import com.c137.data.model.CharacterStatus
import com.c137.data.model.dto.CharacterDto
import com.c137.data.model.dto.CharacterLocation
import com.c137.data.model.mapper.dto.toDataModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.UUID

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [32])
class CharacterDtoMapperTest {

    @Test
    fun `should map a character dto to a data character correctly`() {
        val dto = CharacterDto(
            id = UUID.randomUUID().hashCode(),
            name = UUID.randomUUID().toString(),
            image = UUID.randomUUID().toString(),
            species = UUID.randomUUID().toString(),
            origin = CharacterLocation(
                name = UUID.randomUUID().toString(),
                url = "https://rickandmortyapi.com/api/location/1"
            ),
            location = CharacterLocation(
                name = UUID.randomUUID().toString(),
                url = "https://rickandmortyapi.com/api/location/2"
            ),
            status = CharacterStatus.Alive.name
        )

        val dataModel = dto.toDataModel()

        assertEquals(dto.id, dataModel.id)
        assertEquals(dto.name, dataModel.name)
        assertEquals(dto.image, dataModel.image)
        assertEquals(dto.species, dataModel.species)
        assertEquals(dto.origin.id, dataModel.originId)
        assertEquals(dto.location.id, dataModel.locationId)
        assertEquals(dto.status, dataModel.status.name)
    }
}