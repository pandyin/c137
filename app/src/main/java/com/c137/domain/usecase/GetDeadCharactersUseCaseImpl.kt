package com.c137.domain.usecase

import com.c137.domain.usecase.api.CharacterRepository
import com.c137.domain.usecase.model.mapper.CharacterDomainMapper
import com.c137.presentation.api.GetDeadCharactersUseCase
import com.c137.domain.usecase.model.CharacterPresentation
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

@ViewModelScoped
class GetDeadCharactersUseCaseImpl @Inject constructor(private val repository: CharacterRepository) :
    GetDeadCharactersUseCase {

    override fun execute(): Flowable<List<CharacterPresentation>> {
        return repository.getDeadCharacters()
            .map { it.map { domain -> CharacterDomainMapper().map(domain) } }
    }
}