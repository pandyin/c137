package co.swapi.starships.data.repository.datastore.remote.api

import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface StarshipsApi {

    @GET("starships/")
    fun getAllStarships(): Single<ResponseBody>
}