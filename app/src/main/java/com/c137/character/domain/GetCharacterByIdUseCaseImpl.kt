package com.c137.character.domain

import com.c137.character.data.model.Character
import com.c137.character.data.repository.CharacterRepository
import io.reactivex.rxjava3.core.Flowable

class GetCharacterByIdUseCaseImpl(private val repository: CharacterRepository) :
    GetCharacterByIdUseCase {

    override fun execute(id: Int): Flowable<Character> {
        return repository.getCharacterById(id)
    }
}