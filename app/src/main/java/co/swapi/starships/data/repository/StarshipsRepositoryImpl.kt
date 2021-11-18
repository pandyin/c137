package co.swapi.starships.data.repository

import co.swapi.starships.data.model.Starship
import co.swapi.starships.data.repository.datastore.remote.api.StarshipsApi
import io.reactivex.rxjava3.core.Single

class StarshipsRepositoryImpl(private val remoteApi: StarshipsApi) : StarshipsRepository {

    override fun getAllStarships(): Single<Starship> {
        return Single.just(remoteApi.getAllStarships().execute().body())
    }
}