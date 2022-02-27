package com.c137.character.data.repository.datastore.di

import com.c137.R
import com.c137.character.data.repository.datastore.remote.api.CharacterApi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun networkModule(baseUrl: String? = null) = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl ?: androidContext().getString(R.string.swapi_base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    factory<CharacterApi> {
        get<Retrofit>().create(CharacterApi::class.java)
    }
}