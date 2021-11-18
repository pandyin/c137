package co.swapi.starships.domain

import io.reactivex.rxjava3.core.Single

interface GetAllStarshipsUseCase {

    fun execute(): Single<String>
}