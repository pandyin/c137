package com.c137.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.c137.data.model.mapper.toDomainModel
import com.c137.data.repository.api.CharacterPagingSource
import com.c137.domain.api.PagingCharacterRepository
import com.c137.domain.model.DomainCharacter
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class PagingCharacterRepositoryImpl @Inject constructor(private val pagingSource: CharacterPagingSource) :
    PagingCharacterRepository {

    override fun getPagingCharacter(): Flow<PagingData<DomainCharacter>> =
        Pager(config = PagingConfig(pageSize = 20)) { pagingSource }.flow.map { it.toDomainModel() }
}