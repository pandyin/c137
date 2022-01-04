package com.c137.classical.di

import com.c137.characters.data.repository.datastore.remote.api.CharactersApi
import com.google.gson.JsonObject
import okhttp3.Request
import okio.Timeout
import org.koin.dsl.module
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

val stubCharactersApiModule = module {
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