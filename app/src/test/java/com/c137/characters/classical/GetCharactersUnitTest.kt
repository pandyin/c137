package com.c137.characters.classical

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.c137.RxTrampolineSchedulerRule
import com.c137.characters.data.repository.datastore.di.datastoreModule
import com.c137.characters.data.repository.di.repositoryModule
import com.c137.characters.domain.di.useCaseModule
import com.c137.characters.presentation.CharactersViewModel
import com.c137.characters.presentation.CharactersViewModelImpl
import com.c137.characters.presentation.di.viewModelModule
import com.c137.characters.mockist.di.stubApiModule
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

    //setup data.
    @Before
    fun setup() {
        startKoin {
            modules(
                listOf(
                    stubApiModule,
                    datastoreModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

    //exercise and verity data.
    @Test
    fun getCharacters_assertComplete() {
        val viewModel = get<CharactersViewModel>()
        viewModel.getCharacters()
            .test()
            .assertValue(emptyList())
            .assertComplete()
    }

    //exercise and verity data.
    @Test
    fun getCharacters_assertComplete_withoutDI() {
        val viewModel: CharactersViewModel = CharactersViewModelImpl()
        viewModel.getCharacters()
            .test()
            .assertValue(emptyList())
            .assertComplete()
    }

    //teardown.
    @After
    fun teardown() {
        stopKoin()
    }
}