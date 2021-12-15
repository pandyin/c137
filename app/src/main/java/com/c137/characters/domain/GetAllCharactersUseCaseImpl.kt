package com.c137.characters.domain

import com.c137.characters.data.model.Characters
import com.c137.characters.data.repository.CharactersRepository
import io.reactivex.rxjava3.core.Single

class GetAllCharactersUseCaseImpl(private val repository: CharactersRepository) : GetAllCharactersUseCase {

    override fun execute(): Single<Characters> {
        return repository.getAllCharacters()
    }
}