package com.c137.character.presentation

import com.c137.character.data.model.Character
import com.c137.character.data.model.Status
import com.c137.character.domain.GetCharacterByIdUseCase
import com.c137.character.domain.GetCharactersByStatusUseCase
import com.c137.character.domain.GetCharactersUseCase
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

class CharacterViewModelImpl(
    private val getCharactersByStatusUseCase: GetCharactersByStatusUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : CharacterViewModel() {

    override fun getCharactersByStatus(page: Int, status: Status): Single<List<Character>> {
        return getCharactersByStatusUseCase.execute(page, status)
    }

    override fun getCharacters(page: Int): Single<List<Character>> {
        return getCharactersUseCase.execute(page)
    }

    override fun getCharacterById(id: Int): Maybe<Character> {
        return getCharacterByIdUseCase.execute(id)
    }
}