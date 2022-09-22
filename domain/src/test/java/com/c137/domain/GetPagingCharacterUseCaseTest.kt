package com.c137.domain

import com.c137.domain.api.CharacterPagingRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetPagingCharacterUseCaseTest {

    @Test
    fun `a character paging repository should have been called once`(): Unit =
        runBlocking {
            val mockRepository = mockk<CharacterPagingRepository>()
            every { mockRepository.getPagingCharacter() } returns emptyFlow()

            val sut = GetPagingCharacterUseCase(repository = mockRepository)
            sut.execute()

            verify(exactly = 1) { mockRepository.getPagingCharacter() }
        }
}