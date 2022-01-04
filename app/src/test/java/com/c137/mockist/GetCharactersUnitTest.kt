package com.c137.mockist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.c137.RxTrampolineSchedulerRule
import com.c137.characters.data.repository.CharactersRepositoryImpl
import com.c137.characters.data.repository.datastore.remote.CharactersRemoteDatastoreImpl
import com.c137.characters.data.repository.datastore.remote.api.CharactersApi
import com.c137.characters.domain.GetCharactersUseCaseImpl
import com.c137.characters.presentation.CharactersViewModelImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

@RunWith(JUnit4::class)
class GetCharactersUnitTest : KoinTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rxTrampolineSchedulerRule = RxTrampolineSchedulerRule()

    @Before
    fun setup() {
        startKoin {
            modules(emptyList())
        }
    }

    //setup expectations, exercise and verify expectations.
    @Test
    fun getCharacters_assertComplete() {
        val api = mockk<CharactersApi>()
        val remoteDatastore = mockk<CharactersRemoteDatastoreImpl>()
        val repository = mockk<CharactersRepositoryImpl>()
        val useCase = mockk<GetCharactersUseCaseImpl>()
        val viewModel = mockk<CharactersViewModelImpl>()

//        every { api.getCharactersByPage(any()) }
        every { remoteDatastore.getCharacters() } returns Single.just(emptyList())
        every { repository.getCharacters() } returns remoteDatastore.getCharacters()
        every { useCase.execute() } returns repository.getCharacters()
        every { viewModel.getCharacters() } returns useCase.execute()

        viewModel.getCharacters()
            .test()

        verifySequence {
//            api.getCharactersByPage(any())
            remoteDatastore.getCharacters()
            repository.getCharacters()
            useCase.execute()
            viewModel.getCharacters()
        }
    }

    //teardown.
    @After
    fun teardown() {
        stopKoin()
    }
}