package com.c137.characters.domain

import com.c137.characters.data.model.Character
import com.c137.characters.data.model.Status
import com.c137.characters.data.repository.CharactersRepository
import io.reactivex.rxjava3.core.Single

class GetCharactersByStatusUseCaseImpl(private val repository: CharactersRepository) : GetCharactersByStatusUseCase {

    override fun execute(status: Status): Single<List<Character>> {
        return repository.getCharactersByStatus(status)
    }
}