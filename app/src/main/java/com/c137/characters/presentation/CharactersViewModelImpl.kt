package com.c137.characters.presentation

import com.c137.characters.data.model.Character
import com.c137.characters.domain.GetCharactersUseCase
import com.c137.characters.domain.GetCharactersUseCaseImpl
import io.reactivex.rxjava3.core.Single
import org.jetbrains.annotations.TestOnly

class CharactersViewModelImpl(private val getCharacters: GetCharactersUseCase) : CharactersViewModel() {

    @TestOnly
    constructor() : this(GetCharactersUseCaseImpl())

    override fun getCharacters(): Single<List<Character>> {
        return getCharacters.execute()
    }
}