package com.c137.domain

import com.c137.domain.api.EpisodePagingRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetPagingEpisodeUseCaseTest {

    @Test
    fun `a episode paging repository should have been called once`(): Unit =
        runBlocking {
            val mockRepository = mockk<EpisodePagingRepository>()
            every { mockRepository.getPagingEpisode() } returns emptyFlow()

            val sut = GetPagingEpisodesUseCase(repository = mockRepository)
            sut.execute()

            verify(exactly = 1) { mockRepository.getPagingEpisode() }
        }
}