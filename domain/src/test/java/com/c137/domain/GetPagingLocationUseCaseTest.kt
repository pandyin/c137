package com.c137.domain

import com.c137.domain.api.LocationPagingRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetPagingLocationUseCaseTest {

    @Test
    fun `a location paging repository should have been called once`(): Unit =
        runBlocking {
            val mockRepository = mockk<LocationPagingRepository>()
            every { mockRepository.getPagingLocation() } returns emptyFlow()

            val sut = GetPagingLocationUseCase(repository = mockRepository)
            sut.execute()

            verify(exactly = 1) { mockRepository.getPagingLocation() }
        }
}