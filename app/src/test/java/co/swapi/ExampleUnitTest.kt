package co.swapi

import co.swapi.di.testAppModule
import co.swapi.starships.data.repository.di.repositoryModule
import co.swapi.starships.domain.GetAllStarshipsUseCase
import co.swapi.starships.domain.di.useCaseModule
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
    var rxTrampolineSchedulerRule = RxTrampolineSchedulerRule()

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        startKoin {
            modules(
                listOf(
                    testAppModule(mockWebServer.url("/").toString()),
                    repositoryModule,
                    useCaseModule
                )
            )
        }
    }

    @Test
    fun addition_isCorrect() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("hi")
        )

        val useCase: GetAllStarshipsUseCase by inject()
        useCase.execute()
            .test()
            .assertValue("hi")
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