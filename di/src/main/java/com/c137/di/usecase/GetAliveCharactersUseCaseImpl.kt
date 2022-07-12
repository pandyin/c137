package com.c137.di.usecase

import com.c137.api.CharacterRepository
import com.c137.feature.search.api.GetAliveCharactersUseCase
import com.c137.model.CharacterPresentation
import com.c137.model.mapper.CharacterDomainMapper
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