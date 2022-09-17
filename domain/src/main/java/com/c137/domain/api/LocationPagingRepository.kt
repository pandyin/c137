package com.c137.domain.api

import androidx.paging.PagingData
import com.c137.domain.model.DomainLocation
import kotlinx.coroutines.flow.Flow

interface LocationPagingRepository {

    fun getPagingLocation(): Flow<PagingData<DomainLocation>>
}