package com.c137

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.c137.di.testNetworkModule
import com.c137.characters.data.model.Character
import com.c137.characters.data.repository.datastore.di.datastoreModule
import com.c137.characters.data.repository.di.repositoryModule
import com.c137.characters.domain.GetCharactersUseCase
import com.c137.characters.domain.di.useCaseModule
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import java.net.HttpURLConnection
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class ExampleUnitTest : KoinTest {

    private lateinit var mockWebServer: MockWebServer

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rxTrampolineSchedulerRule = RxTrampolineSchedulerRule()

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        startKoin {
            modules(
                listOf(
                    testNetworkModule(mockWebServer.url("/").toString()),
                    datastoreModule,
                    repositoryModule,
                    useCaseModule
                )
            )
        }
    }

    @Test
    fun addition_isCorrect() {
        val body = JsonObject().apply {
            addProperty("name", "Death Star")
        }

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(body.toString())
        )

        val useCase: GetCharactersUseCase by inject()
        useCase.execute()
            .test()
            .assertValue(Gson().fromJson(body, Character::class.java))
            .assertComplete()

        val request = mockWebServer.takeRequest()
        assertEquals(
            String.format("%s%s", mockWebServer.url("/"), "starships/"),
            request.requestUrl.toString()
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()

        stopKoin()
    }
}