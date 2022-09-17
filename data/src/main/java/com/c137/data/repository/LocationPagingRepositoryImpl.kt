package com.c137.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.c137.data.model.mapper.toDomainModel
import com.c137.data.repository.api.LocationPagingSource
import com.c137.domain.api.LocationPagingRepository
import com.c137.domain.model.DomainLocation
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val PAGE_SIZE = 60

@ViewModelScoped
class LocationPagingRepositoryImpl @Inject constructor(private val pagingSource: LocationPagingSource) :
    LocationPagingRepository {

    override fun getPagingCharacter(): Flow<PagingData<DomainLocation>> =
        Pager(config = PagingConfig(pageSize = PAGE_SIZE)) { pagingSource }.flow.map { it.toDomainModel() }
}