package com.c137.characters.mockist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.c137.RxTrampolineSchedulerRule
import com.c137.characters.classical.di.testViewModelModule
import com.c137.characters.data.model.Character
import com.c137.characters.data.model.Status
import com.c137.characters.data.repository.CharactersRepository
import com.c137.characters.data.repository.datastore.local.CharactersLocalDatastore
import com.c137.characters.data.repository.datastore.local.api.CharactersDao
import com.c137.characters.data.repository.datastore.remote.CharactersRemoteDatastore
import com.c137.characters.data.repository.datastore.remote.api.CharactersApi
import com.c137.characters.domain.GetCharactersByStatusUseCase
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
        every { api.getCharactersByStatus(any(), any()) } returns call
        every { api.getCharacters(any()) } returns call

        val remoteDatastore = mockk<CharactersRemoteDatastore>()
        val dummyPage = 0
        every { remoteDatastore.getCharactersByStatus(any()) } returns Single.just(api.getCharactersByStatus(dummyPage, Status.Alive.value))
            .map { emptyList() }
        every { remoteDatastore.getCharacters() } returns Single.just(api.getCharacters(dummyPage))
            .map { emptyList() }

        val repository = mockk<CharactersRepository>()
        every { repository.getCharactersByStatus(any()) } returns remoteDatastore.getCharactersByStatus(Status.Alive)
            .flatMap {
                localDatastore.insertCharacters(it)
                    .andThen(Single.just(it))
            }
        every { repository.getCharacters() } returns remoteDatastore.getCharacters()
            .flatMap {
                localDatastore.insertCharacters(it)
                    .andThen(Single.just(it))
            }

        val getCharactersByStatusUseCase = mockk<GetCharactersByStatusUseCase>()
        every { getCharactersByStatusUseCase.execute(any()) } returns repository.getCharactersByStatus(Status.Alive)

        val getCharactersUseCase = mockk<GetCharactersUseCase>()
        every { getCharactersUseCase.execute() } returns repository.getCharacters()

        val viewModel = get<CharactersViewModel> { parametersOf(getCharactersByStatusUseCase, getCharactersUseCase) }
        viewModel.getCharacters()
            .test()
            .assertComplete()

        viewModel.getCharactersByStatus(Status.Alive)
            .test()
            .assertComplete()

        verify {
            dao.insertCharacters(any())
            localDatastore.insertCharacters(any())
            api.getCharactersByStatus(any(), any())
            remoteDatastore.getCharactersByStatus(any())
            repository.getCharactersByStatus(any())
            getCharactersByStatusUseCase.execute(any())
        }

        verify {
            dao.insertCharacters(any())
            localDatastore.insertCharacters(any())
            api.getCharacters(any())
            remoteDatastore.getCharacters()
            repository.getCharacters()
            getCharactersUseCase.execute()
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
    lateinit var getCharactersByStatusUseCase: GetCharactersByStatusUseCase

    @MockK
    lateinit var getCharactersUseCase: GetCharactersUseCase

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
        every { api.getCharactersByStatus(any(), any()) } returns call
        every { api.getCharacters(any()) } returns call

        val dummyPage = 0
        every { remoteDatastore.getCharactersByStatus(any()) } returns Single.just(api.getCharactersByStatus(dummyPage, Status.Alive.value))
            .map { emptyList() }
        every { remoteDatastore.getCharacters() } returns Single.just(api.getCharacters(dummyPage))
            .map { emptyList() }

        every { repository.getCharactersByStatus(any()) } returns remoteDatastore.getCharactersByStatus(Status.Alive)
            .flatMap {
                localDatastore.insertCharacters(it)
                    .andThen(Single.just(it))
            }
        every { repository.getCharacters() } returns remoteDatastore.getCharacters()
            .flatMap {
                localDatastore.insertCharacters(it)
                    .andThen(Single.just(it))
            }

        every { getCharactersByStatusUseCase.execute(any()) } returns repository.getCharactersByStatus(Status.Alive)
        every { getCharactersUseCase.execute() } returns repository.getCharacters()

        val viewModel: CharactersViewModel = CharactersViewModelImpl(getCharactersByStatusUseCase, getCharactersUseCase)
        viewModel.getCharactersByStatus(Status.Alive)
            .test()
            .assertComplete()

        viewModel.getCharacters()
            .test()
            .assertComplete()

        verify {
            dao.insertCharacters(any())
            localDatastore.insertCharacters(any())
            api.getCharactersByStatus(any(), any())
            remoteDatastore.getCharactersByStatus(any())
            repository.getCharactersByStatus(any())
            getCharactersByStatusUseCase.execute(any())
        }

        verify {
            dao.insertCharacters(any())
            localDatastore.insertCharacters(any())
            api.getCharacters(any())
            remoteDatastore.getCharacters()
            repository.getCharacters()
            getCharactersUseCase.execute()
        }
    }

    //teardown.
    @After
    fun teardown() {
        stopKoin()
    }
}