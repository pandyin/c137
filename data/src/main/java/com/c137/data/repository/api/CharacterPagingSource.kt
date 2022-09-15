package com.c137.data.repository.api

import androidx.paging.PagingSource
import com.c137.data.model.CharacterWithOriginAndLastKnown

abstract class CharacterPagingSource : PagingSource<Int, CharacterWithOriginAndLastKnown>()