package com.c137.character.domain

import com.c137.character.data.model.Character
import com.c137.character.data.model.Status
import com.c137.character.data.repository.CharacterRepository
import io.reactivex.rxjava3.core.Flowable

class GetCharactersByStatusUseCaseImpl(private val repository: CharacterRepository) :
    GetCharactersByStatusUseCase {

    override fun execute(page: Int,status: Status): Flowable<List<Character>> {
        return repository.getCharactersByStatus(page, status)
    }
}