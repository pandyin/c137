package co.swapi.starships.domain

import co.swapi.starships.data.model.Starship
import io.reactivex.rxjava3.core.Single

interface GetAllStarshipsUseCase {

    fun execute(): Single<Starship>
}