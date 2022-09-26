package com.c137.domain

import com.c137.domain.model.DomainLocation
import com.c137.domain.model.mapper.toPresentationModel
import org.assertj.core.api.Assertions
import org.junit.Test
import java.util.UUID

class DomainLocationMapperTest {

    @Test
    fun `should map a presentation location to a domain location correctly`() {
        val dataModel = DomainLocation(
            id = UUID.randomUUID().hashCode(),
            name = UUID.randomUUID().toString(),
            type = UUID.randomUUID().toString(),
            dimension = UUID.randomUUID().toString(),
            residents = listOf(
                UUID.randomUUID().hashCode(),
                UUID.randomUUID().hashCode()
            )
        )

        Assertions.assertThat(dataModel).usingRecursiveComparison()
            .isEqualTo(dataModel.toPresentationModel())
    }
}