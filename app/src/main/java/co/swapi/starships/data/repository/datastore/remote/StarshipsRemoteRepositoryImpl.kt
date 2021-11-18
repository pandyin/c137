package co.swapi.starships.data.repository.datastore.remote

import co.swapi.starships.data.model.Starship
import co.swapi.starships.data.repository.datastore.remote.api.StarshipsApi
import io.reactivex.rxjava3.core.Single

class StarshipsRemoteRepositoryImpl(private val remoteApi: StarshipsApi) : StarshipsRemoteRepository {

    override fun getAllStarships(): Single<Starship> {
        return Single.fromCallable { remoteApi.getAllStarships().execute().body() }
    }
}