package co.swapi.starships.domain

import co.swapi.starships.data.repository.StarshipsRepository
import io.reactivex.rxjava3.core.Completable

class GetAllStarshipsUseCaseImpl(private val repository: StarshipsRepository) : GetAllStarshipsUseCase {

    override fun execute(): Completable {
        return repository.getAllStarships()
    }
}