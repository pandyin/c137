package co.swapi.starships.data.repository.datastore.remote.api

import io.reactivex.rxjava3.core.Completable
import retrofit2.http.GET

interface StarshipsApi {

    @GET("")
    fun getAllStarships(): Completable
}