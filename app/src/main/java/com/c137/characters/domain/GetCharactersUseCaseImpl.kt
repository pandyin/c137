package com.c137.characters.domain

import com.c137.characters.data.model.Character
import com.c137.characters.data.repository.CharactersRepository
import io.reactivex.rxjava3.core.Single

class GetCharactersUseCaseImpl(private val repository: CharactersRepository) : GetCharactersUseCase {

    override fun execute(): Single<List<Character>> {
        return repository.getCharacters()
    }
}