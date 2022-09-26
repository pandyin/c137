package com.c137.data

import com.c137.data.model.DataLocation
import com.c137.data.model.mapper.toDomainModel
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.Test
import java.util.UUID
import kotlin.random.Random

class DataLocationMapperTest {

    @Test
    fun `should map a data location to a domain location correctly`() {
        val dataModel = DataLocation(
            id = UUID.randomUUID().hashCode(),
            name = UUID.randomUUID().toString(),
            type = UUID.randomUUID().toString(),
            dimension = UUID.randomUUID().toString(),
            residents = listOf(Random.nextInt(), Random.nextInt())
        )

        Assertions.assertThat(dataModel).usingRecursiveComparison()
            .isEqualTo(dataModel.toDomainModel())
    }
}