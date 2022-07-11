package com.c137.domain.usecase

import com.c137.domain.usecase.api.CharacterRepository
import com.c137.domain.usecase.model.mapper.CharacterDomainModelMapper
import com.c137.presentation.api.GetCharactersUseCase
import com.c137.presentation.model.CharacterPresentationModel
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

@ViewModelScoped
class GetCharactersUseCaseImpl @Inject constructor(private val repository: CharacterRepository) :
    GetCharactersUseCase {

    override fun execute(): Flowable<List<CharacterPresentationModel>> {
        return repository.getCharacters()
            .map { it.map { domain -> CharacterDomainModelMapper().map(domain) } }
    }
}