package com.c137.characters.data.repository.datastore.remote

import com.c137.characters.common.Catchphrase
import com.c137.characters.data.model.Character
import com.c137.characters.data.repository.datastore.remote.api.CharactersApi
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Single
import okhttp3.Request
import okio.Timeout
import org.jetbrains.annotations.TestOnly
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.net.HttpURLConnection


class CharactersRemoteDatastoreImpl(private val remoteApi: CharactersApi) : CharactersRemoteDatastore {

    @TestOnly
    constructor() : this(object : CharactersApi {
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
    })

    override fun getCharacters(): Single<List<Character>> {
        Catchphrase.test()
        return Single.fromCallable {
            remoteApi.getCharactersByPage(1)
                .execute()
                .body()
        }.map {
            when (it!!.has("results")) {
                true -> {
                    val results = it["results"].asJsonArray
                    val type: Type = object : TypeToken<List<Character>>() {}.type
                    Gson().fromJson(results, type)
                }
                false -> emptyList()
            }
        }
    }
}