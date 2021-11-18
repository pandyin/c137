package co.swapi.di

import co.swapi.starships.data.repository.datastore.remote.api.StarshipsApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun testAppModule(baseUrl: String) = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    factory<StarshipsApi> {
        get<Retrofit>().create(StarshipsApi::class.java)
    }
}