package com.c137.android.presentation

import com.c137.data.model.dto.CharacterDto
import com.c137.data.model.dto.ResultsDto
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class FakeCall : Call<ResultsDto<CharacterDto>> {
    override fun clone(): Call<ResultsDto<CharacterDto>> {
        TODO("Not yet implemented")
    }

    override fun execute(): Response<ResultsDto<CharacterDto>> {
        return Response.success(HttpURLConnection.HTTP_OK, ResultsDto(emptyList()))
    }

    override fun enqueue(callback: Callback<ResultsDto<CharacterDto>>) {
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