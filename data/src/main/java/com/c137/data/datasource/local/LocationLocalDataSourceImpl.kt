package com.c137.data.datasource.local

import com.c137.data.datasource.local.api.LocationDao
import com.c137.data.repository.api.LocationLocalDataSource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LocationLocalDataSourceImpl @Inject constructor(private val dao: LocationDao) :
    LocationLocalDataSource