package com.c137.di

import com.c137.characters.data.repository.datastore.remote.api.CharactersApi
import com.google.gson.JsonObject
import okhttp3.Request
import okio.Timeout
import org.koin.dsl.module
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

fun mockCharacterApi(baseUrl: String) = module {
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

fun dummyCharactersApi() = module {
    factory<CharactersApi> {
        object : CharactersApi {
            override fun getCharactersByPage(page: Int): Call<JsonObject> {
                return object : Call<JsonObject> {
                    override fun clone(): Call<JsonObject> {
                        TODO("Not yet implemented")
                    }

                    override fun execute(): Response<JsonObject> {
                        return Response.success(HttpURLConnection.HTTP_OK, JsonObject())
                    }

                    override fun enqueue(callback: Callback<JsonObject>) {
                        TODO("Not yet implemented")
                    }

                    override fun isExecuted(): Boolean {
                        TODO("Not yet implemented")
                    }

                    override fun cancel() {
                        TODO("Not yet implemented")
                    }

                    override fun isCanceled(): Boolean {
                        TODO("Not yet implemented")
                    }

                    override fun request(): Request {
                        TODO("Not yet implemented")
                    }

                    override fun timeout(): Timeout {
                        TODO("Not yet implemented")
                    }
                }
            }
        }
    }
}