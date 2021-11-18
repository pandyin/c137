package co.swapi.di

import co.swapi.starships.data.repository.datastore.remote.api.StarshipsApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

fun testAppModule(baseUrl: String) = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    factory<StarshipsApi> {
        get<Retrofit>().create(StarshipsApi::class.java)
    }
}