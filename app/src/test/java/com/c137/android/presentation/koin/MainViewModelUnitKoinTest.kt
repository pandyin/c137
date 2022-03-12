package com.c137.android.presentation.koin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.c137.RxTrampolineSchedulerRule
import com.c137.data.model.Status
import com.c137.data.repository.datastore.di.koin.networkKoinModule
import com.c137.data.repository.datastore.local.api.CharacterDao
import com.c137.data.repository.datastore.local.di.koin.characterLocalDatastoreKoinModule
import com.c137.data.repository.datastore.remote.di.koin.characterRemoteDatastoreKoinModule
import com.c137.data.repository.di.koin.characterRepositoryKoinModule
import com.c137.domain.di.koin.useCaseKoinModule
import com.c137.presentation.MainViewModel
import com.c137.presentation.di.koin.viewModelKoinModule
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
import kotlin.test.assertEquals

class MainViewModelUnitKoinTest : AutoCloseKoinTest() {

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
                networkKoinModule(mockWebServer.url("/").toString()),
                characterLocalDatastoreKoinModule,
                characterRemoteDatastoreKoinModule,
                characterRepositoryKoinModule,
                useCaseKoinModule,
                viewModelKoinModule
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

        val sut = get<MainViewModel>()
        sut.getCharacters()
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

        val sut = get<MainViewModel>()
        sut.getCharactersByStatus(status)
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
}