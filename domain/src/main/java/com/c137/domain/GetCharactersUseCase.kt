package com.c137.domain

import com.c137.domain.api.CharacterRepository
import com.c137.domain.model.PresentationCharacter
import com.c137.domain.model.mapper.CharacterDomainMapper
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

@ViewModelScoped
class GetCharactersUseCase @Inject constructor(private val repository: CharacterRepository) {

    fun execute(): Flowable<List<PresentationCharacter>> {
        return repository.getCharacters()
            .map { it.map { domain -> CharacterDomainMapper().map(domain) } }
    }
}