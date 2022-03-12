package com.c137.android.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.c137.RxTrampolineSchedulerRule
import com.c137.android.presentation.doubles.DummyGetCharacterByIdUseCase
import com.c137.android.presentation.doubles.DummyGetCharactersByStatusUseCase
import com.c137.android.presentation.doubles.FakeCharacterDao
import com.c137.data.model.Status
import com.c137.data.model.dto.CharacterDto
import com.c137.data.model.dto.CharacterDtoMapper
import com.c137.data.repository.CharacterRepositoryImpl
import com.c137.data.repository.datastore.local.CharacterLocalDatastoreImpl
import com.c137.data.repository.datastore.remote.CharacterRemoteDatastoreImpl
import com.c137.data.repository.datastore.remote.api.CharacterService
import com.c137.domain.GetCharactersUseCaseImpl
import com.c137.presentation.MainViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.mockk.spyk
import io.mockk.verify
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.util.*
import kotlin.test.assertEquals

class MainViewModelUnitTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rxTrampolineSchedulerRule = RxTrampolineSchedulerRule()

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun get_characters() {
        val expectedCharacter = CharacterDto(UUID.randomUUID().hashCode(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            Status.Alive.name
        )

        val results = JsonArray()
        results.add(JsonParser.parseString(Gson().toJson(expectedCharacter)))

        val body = JsonObject()
        body.add("results", results)

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(body.toString())
        )

        val dao = spyk(FakeCharacterDao())

        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        val service = retrofit.create(CharacterService::class.java)

        val localDatastore = CharacterLocalDatastoreImpl(dao)
        val remoteDatastore = CharacterRemoteDatastoreImpl(service)
        val repository = CharacterRepositoryImpl(localDatastore, remoteDatastore)
        val useCase = GetCharactersUseCaseImpl(repository)

        val sut = MainViewModel(DummyGetCharacterByIdUseCase(),
            useCase,
            DummyGetCharactersByStatusUseCase())

        sut.getCharacters()
            .test()
            .assertComplete()

        verify(exactly = 1) {
            dao.getCharacters()
            dao.insertCharacters(listOf(CharacterDtoMapper().map(expectedCharacter)))
        }

        assertEquals(
            "/character?page=1",
            mockWebServer.takeRequest().path
        )
    }
}