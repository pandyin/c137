package com.c137.domain

import androidx.paging.PagingData
import com.c137.domain.api.CharacterPagingRepository
import com.c137.domain.model.PresentationCharacter
import com.c137.domain.model.mapper.toPresentationModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class GetPagingLocationUseCase @Inject constructor(private val repository: CharacterPagingRepository) {

    fun execute(): Flow<PagingData<PresentationCharacter>> =
        repository.getPagingCharacter().map { it.toPresentationModel() }
}