package com.c137.character.domain

import com.c137.character.data.model.Character
import com.c137.character.data.repository.CharacterRepository
import io.reactivex.rxjava3.core.Single

class GetCharactersUseCaseImpl(private val repository: CharacterRepository) : GetCharactersUseCase {

    override fun execute(page: Int): Single<List<Character>> {
        return repository.getCharacters(page)
    }
}