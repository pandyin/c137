package co.swapi.starships.data.repository

import co.swapi.starships.data.model.Starship
import io.reactivex.rxjava3.core.Single

interface StarshipsRepository {

    fun getAllStarships(): Single<Starship>
}