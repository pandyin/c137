package com.c137.mockist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.c137.RxTrampolineSchedulerRule
import com.c137.characters.data.repository.CharactersRepositoryImpl
import com.c137.characters.data.repository.datastore.remote.CharactersRemoteDatastoreImpl
import com.c137.characters.data.repository.datastore.remote.api.CharactersApi
import com.c137.characters.domain.GetCharactersUseCaseImpl
import com.c137.characters.presentation.CharactersViewModelImpl
import com.google.gson.JsonObject
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
import retrofit2.Call
import retrofit2.Response
import java.net.HttpURLConnection

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
        val call = mockk<Call<JsonObject>>()
        every { call.execute() } returns Response.success(HttpURLConnection.HTTP_OK, JsonObject())

        val api = mockk<CharactersApi>()
        every { api.getCharactersByPage(any()) } returns call

        val remoteDatastore = mockk<CharactersRemoteDatastoreImpl>()
        val dummyPage = 0
        every { remoteDatastore.getCharacters() } returns Single.just(api.getCharactersByPage(dummyPage))
            .map { emptyList() }

        val repository = mockk<CharactersRepositoryImpl>()
        every { repository.getCharacters() } returns remoteDatastore.getCharacters()

        val useCase = mockk<GetCharactersUseCaseImpl>()
        every { useCase.execute() } returns repository.getCharacters()

        val viewModel = mockk<CharactersViewModelImpl>()
        every { viewModel.getCharacters() } returns useCase.execute()

        viewModel.getCharacters()
            .test()

        verifySequence {
            api.getCharactersByPage(any())
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