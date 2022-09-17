package com.c137.data.datasource.local.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.c137.data.model.DataEpisode
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(episodes: List<DataEpisode>)

    @Query("select * from episode order by airDate asc")
    fun getEpisodes(): Flow<List<DataEpisode>>
}