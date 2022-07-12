package com.c137.domain.usecase

import com.c137.domain.usecase.api.CharacterRepository
import com.c137.domain.usecase.model.mapper.CharacterDomainMapper
import com.c137.presentation.api.GetAliveCharactersUseCase
import com.c137.presentation.model.CharacterPresentation
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

@ViewModelScoped
class GetAliveCharactersUseCaseImpl @Inject constructor(private val repository: CharacterRepository) :
    GetAliveCharactersUseCase {

    override fun execute(): Flowable<List<CharacterPresentation>> {
        return repository.getAliveCharacters()
            .map { it.map { domain -> CharacterDomainMapper().map(domain) } }
    }
}