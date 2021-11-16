package co.swapi.starships.presentation

import androidx.lifecycle.ViewModel
import co.swapi.starships.domain.GetAllStarshipsUseCase
import io.reactivex.rxjava3.core.Completable

class StarshipsViewModel(private val getAllStarshipsUseCase: GetAllStarshipsUseCase) : ViewModel() {

    fun getAllStarships(): Completable {
        return getAllStarshipsUseCase.execute()
    }
}