package com.c137.data

import com.c137.data.model.CharacterStatus
import com.c137.data.model.DataCharacter
import com.c137.data.model.mapper.toDomainModel
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.UUID
import kotlin.random.Random

class DataCharacterMapperTest {

    @Test
    fun `should map a data character to a domain character correctly`() {
        val dataModel = DataCharacter(
            id = UUID.randomUUID().hashCode(),
            name = UUID.randomUUID().toString(),
            image = UUID.randomUUID().toString(),
            species = UUID.randomUUID().toString(),
            originId = Random.nextInt(),
            locationId = Random.nextInt(),
            status = CharacterStatus.Alive
        )

        val domainModel = dataModel.toDomainModel()
        assertEquals(dataModel.id, domainModel.id)
        assertEquals(dataModel.name, domainModel.name)
        assertEquals(dataModel.image, domainModel.image)
        assertEquals(dataModel.species, domainModel.species)
//            assertEquals(dataModel.originId, domainModel.origin.id)
//            assertEquals(dataModel.locationId, domainModel.lastKnown.id)
        assertEquals(dataModel.status == CharacterStatus.Dead, domainModel.isDead)
    }
}