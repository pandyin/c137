package co.swapi.starships.domain

import io.reactivex.rxjava3.core.Completable

class GetAllStarshipsUseCase {

    fun execute(): Completable {
        return Completable.never()
    }
}