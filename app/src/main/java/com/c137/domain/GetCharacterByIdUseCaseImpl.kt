package com.c137.domain

import com.c137.data.model.Character
import com.c137.data.repository.CharacterRepository
import com.c137.di.ActivityScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityScope
class GetCharacterByIdUseCaseImpl @Inject constructor(private val repository: CharacterRepository) :
    GetCharacterByIdUseCase {

    override fun execute(id: Int): Flow<Character> {
        return repository.getCharacterById(id)
    }
}