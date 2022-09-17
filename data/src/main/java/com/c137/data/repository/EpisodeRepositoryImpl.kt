package com.c137.data.repository

import com.c137.data.model.mapper.toDomainModel
import com.c137.data.repository.api.EpisodeLocalDataSource
import com.c137.data.repository.api.EpisodeRemoteDataSource
import com.c137.domain.api.EpisodeRepository
import com.c137.domain.model.DomainEpisode
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@ViewModelScoped
class EpisodeRepositoryImpl @Inject constructor(
    private val localDataSource: EpisodeLocalDataSource,
    private val remoteDataSource: EpisodeRemoteDataSource

) : EpisodeRepository {

    override fun getEpisodes(): Flow<List<DomainEpisode>> {
        return localDataSource.getEpisodes()
            .onStart { localDataSource.insertEpisodes(remoteDataSource.getEpisodes().results) }
            .map { it.map { episode -> episode.toDomainModel() } }
    }
}