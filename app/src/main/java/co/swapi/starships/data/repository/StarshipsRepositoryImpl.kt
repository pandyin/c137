package co.swapi.starships.data.repository

import co.swapi.starships.data.repository.datastore.remote.api.StarshipsApi
import io.reactivex.rxjava3.core.Completable

class StarshipsRepositoryImpl(private val remoteApi: StarshipsApi) : StarshipsRepository {

    override fun getAllStarships(): Completable {
        return remoteApi.getAllStarships().ignoreElement()
    }
}