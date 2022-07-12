package com.c137.domain.usecase

import com.c137.common.model.CharacterStatus
import com.c137.domain.usecase.api.CharacterRepository
import com.c137.domain.usecase.model.mapper.CharacterDomainMapper
import com.c137.presentation.api.GetCharactersByStatusUseCase
import com.c137.presentation.model.CharacterPresentation
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

@ViewModelScoped
class GetCharactersByStatusUseCaseImpl @Inject constructor(private val repository: CharacterRepository) :
    GetCharactersByStatusUseCase {

    override fun execute(status: CharacterStatus): Flowable<List<CharacterPresentation>> {
        return repository.getCharactersByStatus(status)
            .map { it.map { domain -> CharacterDomainMapper().map(domain) } }
    }
}