package com.c137.data.repository.api

import androidx.paging.PagingSource
import com.c137.data.model.DataCharacter

abstract class CharacterPagingSource : PagingSource<Int, DataCharacter>()