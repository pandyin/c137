package com.c137.data.datasource.local

import com.c137.data.datasource.local.api.EpisodeDao
import com.c137.data.model.dto.EpisodeDto
import com.c137.data.model.mapper.dto.toDataModel
import com.c137.data.repository.api.EpisodeLocalDataSource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class EpisodeLocalDataSourceImpl @Inject constructor(private val dao: EpisodeDao) :
    EpisodeLocalDataSource {

    override suspend fun insertEpisodes(episodes: List<EpisodeDto>) =
        dao.insertEpisodes(episodes.map { it.toDataModel() })

    override fun getEpisodes() = dao.getEpisodes()
}