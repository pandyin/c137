package com.c137.domain.usecase

import com.c137.domain.usecase.api.CharacterRepository
import com.c137.domain.usecase.model.mapper.CharacterDomainMapper
import com.c137.presentation.api.GetCharacterByIdUseCase
import com.c137.domain.usecase.model.CharacterPresentation
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class GetCharacterByIdUseCaseImpl @Inject constructor(private val repository: CharacterRepository) :
    GetCharacterByIdUseCase {

    override fun execute(id: Int): Flow<CharacterPresentation> {
        return repository.getCharacterById(id)
            .map { CharacterDomainMapper().map(it) }
    }
}