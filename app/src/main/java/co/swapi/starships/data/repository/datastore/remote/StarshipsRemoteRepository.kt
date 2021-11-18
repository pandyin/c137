package co.swapi.starships.data.repository.datastore.remote

import co.swapi.starships.data.model.Starship
import io.reactivex.rxjava3.core.Single

interface StarshipsRemoteRepository {

    fun getAllStarships(): Single<Starship>
}