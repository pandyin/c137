package com.c137.domain

import com.c137.domain.model.DomainCharacter
import com.c137.domain.model.DomainLocation
import com.c137.domain.model.mapper.toPresentationModel
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.Test
import java.util.UUID
import kotlin.random.Random

class DomainCharacterMapperTest {

    @Test
    fun `should map a presentation character to a domain character correctly`(): Unit =
        runBlocking {
            val dataModel = DomainCharacter(
                id = UUID.randomUUID().hashCode(),
                name = UUID.randomUUID().toString(),
                image = UUID.randomUUID().toString(),
                species = UUID.randomUUID().toString(),
                isDead = Random.nextBoolean()
            ).apply {
                origin = DomainLocation(
                    id = UUID.randomUUID().hashCode(),
                    name = UUID.randomUUID().toString(),
                    type = UUID.randomUUID().toString(),
                    dimension = UUID.randomUUID().toString(),
                    residents = listOf(
                        UUID.randomUUID().hashCode(),
                        UUID.randomUUID().hashCode()
                    )
                )
                lastKnown = DomainLocation(
                    id = UUID.randomUUID().hashCode(),
                    name = UUID.randomUUID().toString(),
                    type = UUID.randomUUID().toString(),
                    dimension = UUID.randomUUID().toString(),
                    residents = listOf(
                        UUID.randomUUID().hashCode(),
                        UUID.randomUUID().hashCode()
                    )
                )
            }

            Assertions.assertThat(dataModel).usingRecursiveComparison()
                .isEqualTo(dataModel.toPresentationModel())
        }
}