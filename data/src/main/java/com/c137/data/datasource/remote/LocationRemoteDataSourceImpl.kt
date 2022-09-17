package com.c137.data.datasource.remote

import com.c137.data.repository.api.LocationRemoteDataSource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LocationRemoteDataSourceImpl @Inject constructor() : LocationRemoteDataSource