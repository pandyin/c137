package com.c137.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode")
data class DataEpisode(
    @PrimaryKey override val id: Int,
    val name: String,
    val airDate: Long,
    val episode: String,
    val characters: List<Int>
) : BaseDataModel(id)