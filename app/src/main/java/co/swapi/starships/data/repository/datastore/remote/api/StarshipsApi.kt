package co.swapi.starships.data.repository.datastore.remote.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface StarshipsApi {

    @GET("starships/")
    fun getAllStarships(): Call<ResponseBody>
}