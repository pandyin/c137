package com.c137.domain.model

import java.util.Date

data class PresentationEpisode(
    val id: Int,
    val name: String,
    val airDate: Date,
    val episode: String,
    val characters: List<Int>
) : BasePresentationModel()