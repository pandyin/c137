package com.c137.domain

import com.c137.data.model.Character
import com.c137.data.model.Status
import com.c137.data.repository.CharacterRepository
import io.reactivex.rxjava3.core.Flowable

class GetCharactersByStatusUseCaseImpl(private val repository: CharacterRepository) :
    GetCharactersByStatusUseCase {

    override fun execute(page: Int,status: Status): Flowable<List<Character>> {
        return repository.getCharactersByStatus(page, status)
    }
}