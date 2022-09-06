package com.c137.data.repository

import androidx.paging.PagingData
import com.c137.data.repository.api.CharacterPagingSource
import com.c137.domain.api.PagingCharacterRepository
import com.c137.domain.model.DomainCharacter
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class PagingCharacterRepositoryImpl @Inject constructor(private val pagingSource: CharacterPagingSource) :
    PagingCharacterRepository {

    override fun getPagingCharacter(page: Int): Flow<PagingData<DomainCharacter>> {
        TODO("Not yet implemented")
    }
}