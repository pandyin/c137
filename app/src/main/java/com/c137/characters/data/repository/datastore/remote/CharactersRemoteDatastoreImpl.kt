package com.c137.characters.data.repository.datastore.remote

import com.c137.characters.common.ResultConverter
import com.c137.characters.data.model.Character
import com.c137.characters.data.model.Status
import com.c137.characters.data.repository.datastore.remote.api.CharactersApi
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import okhttp3.Request
import okio.Timeout
import org.jetbrains.annotations.TestOnly
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection


class CharactersRemoteDatastoreImpl(private val remoteApi: CharactersApi) : CharactersRemoteDatastore {

    @TestOnly
    constructor() : this(object : CharactersApi {
        override fun getCharactersByStatus(page: Int, status: String): Call<JsonObject> {
            return CallStub()
        }

        override fun getCharacters(page: Int): Call<JsonObject> {
            return CallStub()
        }
    })

    override fun getCharactersByStatus(status: Status): Single<List<Character>> {
        return Single.fromCallable {
            remoteApi.getCharacters(1)
                .execute()
                .body()
        }.map {
            ResultConverter.convert(it)
        }
    }

    override fun getCharacters(): Single<List<Character>> {
        return Single.fromCallable {
            remoteApi.getCharacters(1)
                .execute()
                .body()
        }.map {
            ResultConverter.convert(it)
        }
    }
}

private class CallStub : Call<JsonObject> {
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