package co.swapi.starships.domain

import io.reactivex.rxjava3.core.Completable

interface GetAllStarshipsUseCase {

    fun execute(): Completable
}