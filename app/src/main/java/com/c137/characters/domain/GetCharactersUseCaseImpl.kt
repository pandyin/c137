package com.c137.characters.domain

import com.c137.characters.data.model.Character
import com.c137.characters.data.repository.CharactersRepository
import com.c137.characters.data.repository.CharactersRepositoryImpl
import io.reactivex.rxjava3.core.Single
import org.jetbrains.annotations.TestOnly

class GetCharactersUseCaseImpl(private val repository: CharactersRepository) : GetCharactersUseCase {

    @TestOnly
    constructor() : this(CharactersRepositoryImpl())

    override fun execute(): Single<List<Character>> {
        return repository.getCharacters()
    }
}