package com.c137.characters.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.c137.RxTrampolineSchedulerRule
import com.c137.characters.data.repository.datastore.di.localDatastoreModule
import com.c137.characters.data.repository.datastore.di.networkModule
import com.c137.characters.data.repository.datastore.di.remoteDatastoreModule
import com.c137.characters.data.repository.datastore.local.api.CharactersDao
import com.c137.characters.data.repository.di.repositoryModule
import com.c137.characters.domain.di.useCaseModule
import com.c137.characters.presentation.di.viewModelModule
import com.google.gson.JsonObject
import io.mockk.mockkClass
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import java.net.HttpURLConnection
import kotlin.test.assertEquals

class CharactersViewModelUnitTest : KoinTest {

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

        val dao = declareMock<CharactersDao>()

        val sut = get<CharactersViewModel>()
        sut.getCharacters()
            .test()
            .assertComplete()

        assertEquals("/character?page=1", mockWebServer.takeRequest().path)
    }
}