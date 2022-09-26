package com.c137.data

import com.c137.data.model.DataEpisode
import com.c137.data.model.mapper.toDomainModel
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.Test
import java.util.Calendar
import java.util.Date
import java.util.UUID
import kotlin.random.Random

class DataEpisodeMapperTest {

    @Test
    fun `should map a data episode to a domain episode correctly`() {
        val dataModel = DataEpisode(
            id = UUID.randomUUID().hashCode(),
            name = UUID.randomUUID().toString(),
            airDate = Calendar.getInstance().timeInMillis,
            episode = UUID.randomUUID().toString(),
            characters = listOf(Random.nextInt(), Random.nextInt())
        )

        Assertions.assertThat(dataModel).usingRecursiveComparison()
            .withComparatorForFields(
                { t, t1 -> (t as Long).compareTo((t1 as Date).time) },
                "airDate"
            )
            .isEqualTo(dataModel.toDomainModel())
    }
}