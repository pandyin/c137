package co.swapi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.swapi.di.testAppModule
import co.swapi.starships.data.model.Starship
import co.swapi.starships.data.repository.datastore.di.datastoreModule
import co.swapi.starships.data.repository.di.repositoryModule
import co.swapi.starships.domain.GetAllStarshipsUseCase
import co.swapi.starships.domain.di.useCaseModule
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
                    testAppModule(mockWebServer.url("/").toString()),
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

        val useCase: GetAllStarshipsUseCase by inject()
        useCase.execute()
            .test()
            .assertValue(Gson().fromJson(body, Starship::class.java))
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