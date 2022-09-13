package com.c137.domain

import androidx.paging.PagingData
import com.c137.domain.api.LocationPagingRepository
import com.c137.domain.model.PresentationLocation
import com.c137.domain.model.mapper.toPresentationModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class GetPagingLocationUseCase @Inject constructor(private val repository: LocationPagingRepository) {

    fun execute(): Flow<PagingData<PresentationLocation>> =
        repository.getPagingCharacter().map { it.toPresentationModel() }
}