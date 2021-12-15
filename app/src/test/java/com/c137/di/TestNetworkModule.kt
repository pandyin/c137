package com.c137.di

import com.c137.characters.data.repository.datastore.remote.api.CharactersApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun testNetworkModule(baseUrl: String) = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    factory<CharactersApi> {
        get<Retrofit>().create(CharactersApi::class.java)
    }
}