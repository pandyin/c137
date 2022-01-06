package com.c137.characters.mockist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.c137.RxTrampolineSchedulerRule
import com.c137.characters.classical.di.testViewModelModule
import com.c137.characters.data.model.Character
import com.c137.characters.data.repository.CharactersRepository
import com.c137.characters.data.repository.datastore.local.CharactersLocalDatastore
import com.c137.characters.data.repository.datastore.local.api.CharactersDao
import com.c137.characters.data.repository.datastore.remote.CharactersRemoteDatastore
import com.c137.characters.data.repository.datastore.remote.api.CharactersApi
import com.c137.characters.domain.GetCharactersUseCase
import com.c137.characters.presentation.CharactersViewModel
import com.c137.characters.presentation.CharactersViewModelImpl
import com.google.gson.JsonObject
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import org.koin.test.get
import retrofit2.Call

@RunWith(JUnit4::class)
class GetCharactersUnitTest : KoinTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rxTrampolineSchedulerRule = RxTrampolineSchedulerRule()

    @Before
    fun setup() {
        startKoin {
            modules(testViewModelModule)
        }
    }

    //setup expectations, exercise and verify expectations.
    @Test
    fun getCharacters_assertComplete() {
        val dao = mockk<CharactersDao>()
        every { dao.insertCharacters(any()) } returns Completable.complete()

        val localDatastore = mockk<CharactersLocalDatastore>()
        val dummyCharacters = emptyList<Character>()
        every { localDatastore.insertCharacters(any()) } returns dao.insertCharacters(dummyCharacters)

        val call = mockk<Call<JsonObject>>()
        val api = mockk<CharactersApi>()
        every { api.getCharactersByPage(any()) } returns call

        val remoteDatastore = mockk<CharactersRemoteDatastore>()
        val dummyPage = 0
        every { remoteDatastore.getCharacters() } returns Single.just(api.getCharactersByPage(dummyPage))
            .map { emptyList() }

        val repository = mockk<CharactersRepository>()
        every { repository.getCharacters() } returns remoteDatastore.getCharacters()
            .flatMap {
                localDatastore.insertCharacters(it)
                    .andThen(Single.just(it))
            }

        val useCase = mockk<GetCharactersUseCase>()
        every { useCase.execute() } returns repository.getCharacters()

        val viewModel = get<CharactersViewModel> { parametersOf(useCase) }
        viewModel.getCharacters()
            .test()
            .assertComplete()

        verify(exactly = 1) {
            dao.insertCharacters(any())
            localDatastore.insertCharacters(any())
            api.getCharactersByPage(any())
            remoteDatastore.getCharacters()
            repository.getCharacters()
            useCase.execute()
        }
    }

    @MockK
    lateinit var dao: CharactersDao

    @MockK
    lateinit var localDatastore: CharactersLocalDatastore

    @MockK
    lateinit var api: CharactersApi

    @MockK
    lateinit var remoteDatastore: CharactersRemoteDatastore

    @MockK
    lateinit var repository: CharactersRepository

    @MockK
    lateinit var useCase: GetCharactersUseCase

    @Before
    fun setUpMockk() = MockKAnnotations.init(this)

    @Before
    fun setupCommonExpectations() {

    }

    //setup expectations, exercise and verify expectations.
    @Test
    fun getCharacters_assertComplete_withoutDI() {
        every { dao.insertCharacters(any()) } returns Completable.complete()

        val dummyCharacters = emptyList<Character>()
        every { localDatastore.insertCharacters(any()) } returns dao.insertCharacters(dummyCharacters)

        val call = mockk<Call<JsonObject>>()
        every { api.getCharactersByPage(any()) } returns call

        val dummyPage = 0
        every { remoteDatastore.getCharacters() } returns Single.just(api.getCharactersByPage(dummyPage))
            .map { emptyList() }

        every { repository.getCharacters() } returns remoteDatastore.getCharacters()
            .flatMap {
                localDatastore.insertCharacters(it)
                    .andThen(Single.just(it))
            }

        every { useCase.execute() } returns repository.getCharacters()

        val viewModel: CharactersViewModel = CharactersViewModelImpl(useCase)
        viewModel.getCharacters()
            .test()
            .assertComplete()

        verify(exactly = 1) {
            dao.insertCharacters(any())
            localDatastore.insertCharacters(any())
            api.getCharactersByPage(any())
            remoteDatastore.getCharacters()
            repository.getCharacters()
            useCase.execute()
        }
    }

    //teardown.
    @After
    fun teardown() {
        stopKoin()
    }
}