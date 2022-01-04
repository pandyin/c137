package com.c137.mockist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.c137.RxTrampolineSchedulerRule
import com.c137.characters.presentation.CharactersViewModel
import com.c137.di.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get

@RunWith(JUnit4::class)
class GetCharactersUnitTest : KoinTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rxTrampolineSchedulerRule = RxTrampolineSchedulerRule()

    @Before
    fun setup() {
        startKoin {
            modules(
                listOf(
                    mockCharacterApiModule,
                    mockCharacterRemoteDatastoreModule,
                    mockCharacterRepositoryModule,
                    mockGetCharactersUseCaseModule,
                    mockCharactersViewModelModule
                )
            )
        }
    }

    @Test
    fun getCharacters_assertComplete() {
        val viewModel = get<CharactersViewModel>()
        viewModel.getCharacters()
            .test()
            .assertValue(emptyList())
            .assertComplete()
    }

    @After
    fun teardown() {
        stopKoin()
    }
}