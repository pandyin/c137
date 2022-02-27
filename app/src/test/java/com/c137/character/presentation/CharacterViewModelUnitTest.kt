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
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.verify
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
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
        every { dao.getCharacters() } returns Flowable.just(emptyList())
        every { dao.insertCharacters(any()) } returns Completable.complete()

        val sut = get<CharacterViewModel>()
        sut.getCharacters(1)
            .test()
            .assertComplete()

        verify(exactly = 1) {
            dao.getCharacters()
            dao.insertCharacters(any())
        }

        assertEquals(
            "/character?page=1",
            mockWebServer.takeRequest().path
        )
    }

    @Test
    fun get_characters_by_status() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(JsonObject().toString())
        )

        val status = Status.Dead

        val dao = declareMock<CharacterDao>()
        every { dao.getCharactersByStatus(status) } returns Flowable.just(emptyList())
        every { dao.insertCharacters(any()) } returns Completable.complete()

        val sut = get<CharacterViewModel>()
        sut.getCharactersByStatus(1, status)
            .test()
            .assertComplete()

        verify(exactly = 1) {
            dao.getCharactersByStatus(status)
            dao.insertCharacters(any())
        }

        assertEquals(
            String.format("/character?page=1&status=%s", status.name),
            mockWebServer.takeRequest().path
        )
    }

    @Test
    fun get_character_by_id() {
        val dummyId = UUID.randomUUID().toString().hashCode()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(JsonObject().apply {
                    addProperty("id", dummyId)
                    addProperty("name", UUID.randomUUID().toString())
                    addProperty("image", UUID.randomUUID().toString())
                    addProperty("status", Status.Alive.name)
                }.toString())
        )

        val dao = declareMock<CharacterDao>()
        every { dao.getCharacterById(dummyId) } returns Flowable.empty()
        every { dao.insertCharacter(any()) } returns Completable.complete()

        val sut = get<CharacterViewModel>()
        sut.getCharacterById(dummyId)
            .test()
            .assertComplete()

        verify(exactly = 1) {
            dao.getCharacterById(dummyId)
            dao.insertCharacter(any())
        }

        assertEquals(
            String.format("/character/%s", dummyId),
            mockWebServer.takeRequest().path
        )
    }
}