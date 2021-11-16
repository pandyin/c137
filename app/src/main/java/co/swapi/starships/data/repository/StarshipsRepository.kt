package co.swapi.starships.data.repository

import io.reactivex.rxjava3.core.Completable

interface StarshipsRepository {

    fun getAllStarships(): Completable
}