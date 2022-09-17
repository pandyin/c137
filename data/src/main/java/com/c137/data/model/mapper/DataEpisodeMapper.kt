package com.c137.data.model.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.c137.data.model.DataEpisode
import com.c137.domain.model.DomainEpisode
import java.util.Date

class DataEpisodeMapper : DataMapper<DataEpisode, DomainEpisode> {

    override fun map(data: DataEpisode): DomainEpisode {
        return DomainEpisode(
            id = data.id,
            name = data.name,
            airDate = Date(data.airDate),
            episode = data.episode,
            characters = data.characters
        )
    }
}

fun DataEpisode.toDomainModel(): DomainEpisode =
    DataEpisodeMapper().map(data = this)

fun PagingData<DataEpisode>.toDomainModel(): PagingData<DomainEpisode> =
    map { DataEpisodeMapper().map(data = it) }