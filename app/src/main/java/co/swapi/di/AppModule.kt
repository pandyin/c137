package co.swapi.di

import co.swapi.R
import co.swapi.starships.data.repository.datastore.remote.api.StarshipsApi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

private fun provideStarshipsApi(retrofit: Retrofit): StarshipsApi {
    return retrofit.create(StarshipsApi::class.java)
}

val appModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .baseUrl(androidContext().getString(R.string.base_url))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    single<StarshipsApi> {
        provideStarshipsApi(get())
    }
}