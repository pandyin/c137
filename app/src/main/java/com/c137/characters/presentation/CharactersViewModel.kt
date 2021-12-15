package com.c137.characters.presentation

import androidx.lifecycle.ViewModel
import com.c137.characters.data.model.Character
import com.c137.characters.domain.GetCharactersUseCase
import io.reactivex.rxjava3.core.Single

class CharactersViewModel(private val getCharacters: GetCharactersUseCase) : ViewModel() {

    fun getCharacters(): Single<List<Character>> {
        return getCharacters.execute()
    }
}