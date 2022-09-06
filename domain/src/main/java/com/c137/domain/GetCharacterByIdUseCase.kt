package com.c137.domain

import com.c137.domain.api.CharacterRepository
import com.c137.domain.model.PresentationCharacter
import com.c137.domain.model.mapper.toPresentationModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class GetCharacterByIdUseCase @Inject constructor(private val repository: CharacterRepository) {

    fun execute(id: Int): Flow<PresentationCharacter> =
        repository.getCharacterById(id = id).map { it.toPresentationModel() }
}