package com.c137.characters.presentation

import androidx.lifecycle.ViewModel
import com.c137.characters.data.model.Characters
import com.c137.characters.domain.GetAllCharactersUseCase
import io.reactivex.rxjava3.core.Single

class CharactersViewModel(private val getAllStarshipsUseCase: GetAllCharactersUseCase) : ViewModel() {

    fun getAllCharacters(): Single<Characters> {
        return getAllStarshipsUseCase.execute()
    }
}