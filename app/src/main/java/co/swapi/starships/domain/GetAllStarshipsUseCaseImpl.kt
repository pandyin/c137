package co.swapi.starships.domain

import co.swapi.starships.data.model.Starship
import co.swapi.starships.data.repository.StarshipsRepository
import io.reactivex.rxjava3.core.Single

class GetAllStarshipsUseCaseImpl(private val repository: StarshipsRepository) : GetAllStarshipsUseCase {

    override fun execute(): Single<Starship> {
        return repository.getAllStarships()
    }
}