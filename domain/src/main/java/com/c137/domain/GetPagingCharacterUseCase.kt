package com.c137.domain

import androidx.paging.PagingData
import com.c137.domain.api.PagingCharacterRepository
import com.c137.domain.model.PresentationCharacter
import com.c137.domain.model.mapper.toPresentationModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class GetPagingCharacterUseCase @Inject constructor(private val repository: PagingCharacterRepository) {

    fun execute(page: Int): Flow<PagingData<PresentationCharacter>> =
        repository.getPagingCharacter(page = page)
            .map { it.toPresentationModel() }
}