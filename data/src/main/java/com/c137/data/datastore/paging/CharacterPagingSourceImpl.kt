package com.c137.data.datastore.paging

import com.c137.data.datastore.local.api.CharacterDao
import com.c137.data.datastore.remote.api.CharacterService
import com.c137.data.repository.api.CharacterPagingSource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CharacterPagingSourceImpl @Inject constructor(
    private val dao: CharacterDao,
    private val service: CharacterService
) : CharacterPagingSource