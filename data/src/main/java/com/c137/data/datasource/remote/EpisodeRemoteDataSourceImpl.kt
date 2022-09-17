package com.c137.data.datasource.remote

import com.c137.data.datasource.remote.api.EpisodeService
import com.c137.data.repository.api.EpisodeRemoteDataSource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class EpisodeRemoteDataSourceImpl @Inject constructor(private val service: EpisodeService) :
    EpisodeRemoteDataSource {

    override suspend fun getEpisodes() = service.getEpisodes()
}