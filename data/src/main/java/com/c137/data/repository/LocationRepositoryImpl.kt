package com.c137.data.repository

import com.c137.data.repository.api.LocationLocalDataSource
import com.c137.domain.api.LocationRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LocationRepositoryImpl @Inject constructor(
    private val localDataSource: LocationLocalDataSource
) : LocationRepository