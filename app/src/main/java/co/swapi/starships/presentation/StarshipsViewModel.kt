package co.swapi.starships.presentation

import androidx.lifecycle.ViewModel
import co.swapi.starships.data.model.Starship
import co.swapi.starships.domain.GetAllStarshipsUseCase
import io.reactivex.rxjava3.core.Single

class StarshipsViewModel(private val getAllStarshipsUseCase: GetAllStarshipsUseCase) : ViewModel() {

    fun getAllStarships(): Single<Starship> {
        return getAllStarshipsUseCase.execute()
    }
}