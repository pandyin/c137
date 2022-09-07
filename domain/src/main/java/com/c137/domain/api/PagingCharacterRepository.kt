package com.c137.domain.api

import androidx.paging.PagingData
import com.c137.domain.model.DomainCharacter
import kotlinx.coroutines.flow.Flow

interface PagingCharacterRepository {

    fun getPagingCharacter(): Flow<PagingData<DomainCharacter>>
}