package com.c137.character.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.c137.RxTrampolineSchedulerRule
import com.c137.character.data.model.Status
import com.c137.character.data.repository.datastore.di.localDatastoreModule
import com.c137.character.data.repository.datastore.di.networkModule
import com.c137.character.data.repository.datastore.di.remoteDatastoreModule
import com.c137.character.data.repository.datastore.local.api.CharacterDao
import com.c137.character.data.repository.di.repositoryModule
import com.c137.character.domain.di.useCaseModule
import com.c137.character.presentation.di.viewModelModule
import com.google.gson.JsonObject
import io.mockk.mockkClass
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import java.net.HttpURLConnection
import java.util.*
import kotlin.test.assertEquals

class CharacterViewModelUnitTest : AutoCloseKoinTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rxTrampolineSchedulerRule = RxTrampolineSchedulerRule()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkClass(clazz)
    }

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        startKoin {
            modules(
                networkModule(mockWebServer.url("/").toString()),
                localDatastoreModule,
                remoteDatastoreModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun get_characters() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(JsonObject().toString())
        )

        val dao = declareMock<CharacterDao>()

        val sut = get<CharacterViewModel>()
        sut.getCharacters(1)
            .test()
            .assertComplete()

        assertEquals(
            "/character?page=1",
            mockWebServer.takeRequest().path
        )
    }

    @Test
    fun get_characters_by_type() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(JsonObject().toString())
        )

        val dao = declareMock<CharacterDao>()

        val sut = get<CharacterViewModel>()
        sut.getCharactersByStatus(1, Status.Dead)
            .test()
            .assertComplete()

        assertEquals(
            "/character?page=1&status=Dead",
            mockWebServer.takeRequest().path
        )
    }

    @Test
    fun get_character_by_id() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(JsonObject().toString())
        )

        val dao = declareMock<CharacterDao>()

        val dummyId = UUID.randomUUID().toString()
        val sut = get<CharacterViewModel>()
        sut.getCharacterById(dummyId.hashCode())
            .test()
            .assertComplete()

        assertEquals(
            String.format("/character/%s", dummyId.hashCode()),
            mockWebServer.takeRequest().path
        )
    }
}