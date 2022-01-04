package com.c137.characters.presentation

import com.c137.characters.data.model.Character
import com.c137.characters.domain.GetCharactersUseCase
import io.reactivex.rxjava3.core.Single

class CharactersViewModelImpl(private val getCharacters: GetCharactersUseCase) : CharactersViewModel() {

    override fun getCharacters(): Single<List<Character>> {
        return getCharacters.execute()
    }
}