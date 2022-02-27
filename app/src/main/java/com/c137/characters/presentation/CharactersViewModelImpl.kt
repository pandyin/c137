package com.c137.characters.presentation

import com.c137.characters.data.model.Character
import com.c137.characters.data.model.Status
import com.c137.characters.domain.GetCharactersByStatusUseCase
import com.c137.characters.domain.GetCharactersUseCase
import io.reactivex.rxjava3.core.Single

class CharactersViewModelImpl(
    private val getCharactersByStatusUseCase: GetCharactersByStatusUseCase,
    private val getCharactersUseCase: GetCharactersUseCase
) : CharactersViewModel() {

    override fun getCharactersByStatus(status: Status): Single<List<Character>> {
        return getCharactersByStatusUseCase.execute(status)
    }

    override fun getCharacters(): Single<List<Character>> {
        return getCharactersUseCase.execute()
    }
}