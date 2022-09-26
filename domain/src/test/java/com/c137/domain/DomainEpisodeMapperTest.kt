package com.c137.domain

import com.c137.domain.model.DomainEpisode
import com.c137.domain.model.mapper.toPresentationModel
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.Test
import java.util.Calendar
import java.util.UUID

class DomainEpisodeMapperTest {

    @Test
    fun `should map a presentation episode to a domain episode correctly`() {
        val dataModel = DomainEpisode(
            id = UUID.randomUUID().hashCode(),
            name = UUID.randomUUID().toString(),
            airDate = Calendar.getInstance().time,
            episode = UUID.randomUUID().toString(),
            characters = listOf(
                UUID.randomUUID().hashCode(),
                UUID.randomUUID().hashCode()
            )
        )

        Assertions.assertThat(dataModel).usingRecursiveComparison()
            .isEqualTo(dataModel.toPresentationModel())
    }
}