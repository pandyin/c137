package com.c137

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.c137.characters.data.repository.datastore.di.datastoreModule
import com.c137.characters.data.repository.di.repositoryModule
import com.c137.characters.domain.GetCharactersUseCase
import com.c137.characters.domain.di.useCaseModule
import com.c137.di.mockCharacterApiModule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(JUnit4::class)
class GetCharactersMockUnitTest : KoinTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rxTrampolineSchedulerRule = RxTrampolineSchedulerRule()

    @Before
    fun setup() {
        startKoin {
            modules(
                listOf(
                    mockCharacterApiModule(),
                    datastoreModule,
                    repositoryModule,
                    useCaseModule
                )
            )
        }
    }

    @Test
    fun getCharacters_assertComplete() {
        val useCase: GetCharactersUseCase by inject()
        useCase.execute()
            .test()
            .assertValue(emptyList())
            .assertComplete()
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}