package com.c137.domain

import com.c137.domain.api.CharacterRepository
import com.c137.domain.model.PresentationCharacter
import com.c137.domain.model.mapper.DomainCharacterMapper
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class GetCharactersByPageUseCase @Inject constructor(private val repository: CharacterRepository) {

    fun execute(page: Int): Flow<List<PresentationCharacter>> {
        return repository.getCharactersByPage(page)
            .map { list -> list.map { DomainCharacterMapper().map(it) } }
    }
}