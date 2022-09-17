package com.c137.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.c137.data.model.mapper.toDomainModel
import com.c137.data.repository.api.CharacterPagingSource
import com.c137.domain.api.CharacterPagingRepository
import com.c137.domain.model.DomainCharacter
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val PAGE_SIZE = 40

@ViewModelScoped
class CharacterPagingRepositoryImpl @Inject constructor(private val pagingSource: CharacterPagingSource) :
    CharacterPagingRepository {

    override fun getPagingCharacter(): Flow<PagingData<DomainCharacter>> =
        Pager(config = PagingConfig(pageSize = PAGE_SIZE)) { pagingSource }.flow.map { it.toDomainModel() }
}