package co.swapi.starships.data.repository.datastore.remote.api

import co.swapi.starships.data.model.Starship
import retrofit2.Call
import retrofit2.http.GET

interface StarshipsApi {

    @GET("starships/")
    fun getAllStarships(): Call<Starship>
}