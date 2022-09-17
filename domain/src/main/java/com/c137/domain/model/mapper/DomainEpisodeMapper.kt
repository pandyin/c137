package com.c137.domain.model.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.c137.domain.model.DomainEpisode
import com.c137.domain.model.PresentationEpisode

class DomainEpisodeMapper : DataMapper<DomainEpisode, PresentationEpisode> {

    override fun map(domain: DomainEpisode): PresentationEpisode {
        return PresentationEpisode(
            id = domain.id,
            name = domain.name,
            airDate = domain.airDate,
            episode = domain.episode,
            characters = domain.characters
        )
    }
}

fun DomainEpisode.toPresentationModel(): PresentationEpisode =
    DomainEpisodeMapper().map(domain = this)

fun PagingData<DomainEpisode>.toPresentationModel(): PagingData<PresentationEpisode> =
    map { DomainEpisodeMapper().map(domain = it) }