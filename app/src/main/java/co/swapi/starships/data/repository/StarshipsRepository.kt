package co.swapi.starships.data.repository

import io.reactivex.rxjava3.core.Single

interface StarshipsRepository {

    fun getAllStarships(): Single<String>
}