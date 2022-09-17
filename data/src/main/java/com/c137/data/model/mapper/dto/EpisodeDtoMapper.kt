package com.c137.data.model.mapper.dto

import android.annotation.SuppressLint
import android.net.Uri
import com.c137.data.model.DataEpisode
import com.c137.data.model.dto.EpisodeDto
import java.text.SimpleDateFormat

class EpisodeDtoMapper : DtoMapper<EpisodeDto, DataEpisode> {

    @SuppressLint("SimpleDateFormat")
    private val pattern = SimpleDateFormat("MMMM dd, yyyy")

    override fun map(dto: EpisodeDto): DataEpisode {
        return DataEpisode(
            id = dto.id,
            name = dto.name,
            airDate = pattern.parse(dto.air_date)!!.time,
            episode = dto.episode,
            characters = dto.characters.mapNotNull {
                Uri.parse(it).lastPathSegment?.trim()?.toIntOrNull()
            }
        )
    }
}

fun EpisodeDto.toDataModel(): DataEpisode =
    EpisodeDtoMapper().map(dto = this)

@Deprecated("to be removed", ReplaceWith(""))
fun List<EpisodeDto>.toDataModel(): List<DataEpisode> =
    map { EpisodeDtoMapper().map(dto = it) }