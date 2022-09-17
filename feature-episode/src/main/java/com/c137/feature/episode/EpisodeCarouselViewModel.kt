package com.c137.feature.episode

import androidx.lifecycle.ViewModel
import com.c137.domain.GetEpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeCarouselViewModel @Inject constructor(
    private val getEpisodesUseCase: GetEpisodesUseCase

) : ViewModel() {

    val episodes by lazy {
        getEpisodesUseCase.execute()
    }
}