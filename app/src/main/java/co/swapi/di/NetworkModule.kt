package co.swapi.di

import co.swapi.R
import co.swapi.starships.data.repository.datastore.remote.api.StarshipsApi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .baseUrl(androidContext().getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    factory<StarshipsApi> {
        get<Retrofit>().create(StarshipsApi::class.java)
    }
}