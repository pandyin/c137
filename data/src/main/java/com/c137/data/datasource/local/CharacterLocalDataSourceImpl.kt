package com.c137.data.datasource.local

import com.c137.data.datasource.local.api.CharacterDao
import com.c137.data.repository.api.CharacterLocalDataSource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CharacterLocalDataSourceImpl @Inject constructor(private val dao: CharacterDao) :
    CharacterLocalDataSource