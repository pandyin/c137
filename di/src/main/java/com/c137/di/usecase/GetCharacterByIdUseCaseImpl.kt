package com.c137.di.usecase

import com.c137.domain.api.CharacterRepository
import com.c137.feature.search.api.GetCharacterByIdUseCase
import com.c137.domain.model.CharacterPresentation
import com.c137.domain.model.mapper.CharacterDomainMapper
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