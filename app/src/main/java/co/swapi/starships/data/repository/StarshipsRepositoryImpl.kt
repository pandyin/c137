package co.swapi.starships.data.repository

import co.swapi.starships.data.model.Starship
import co.swapi.starships.data.repository.datastore.remote.StarshipsRemoteRepository
import io.reactivex.rxjava3.core.Single

class StarshipsRepositoryImpl(private val remoteRepository: StarshipsRemoteRepository) : StarshipsRepository {

    override fun getAllStarships(): Single<Starship> {
        return remoteRepository.getAllStarships()
    }
}