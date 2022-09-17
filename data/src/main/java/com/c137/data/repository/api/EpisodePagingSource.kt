package com.c137.data.repository.api

import androidx.paging.PagingSource
import com.c137.data.model.DataEpisode

abstract class EpisodePagingSource : PagingSource<Int, DataEpisode>()