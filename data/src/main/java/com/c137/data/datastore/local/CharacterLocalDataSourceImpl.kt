package com.c137.data.datastore.local

import com.c137.data.datastore.local.api.CharacterDao
import com.c137.data.model.DataCharacter
import com.c137.data.repository.api.CharacterLocalDataSource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class CharacterLocalDataSourceImpl @Inject constructor(private val dao: CharacterDao) :
    CharacterLocalDataSource