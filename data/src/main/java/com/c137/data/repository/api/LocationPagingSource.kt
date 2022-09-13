package com.c137.data.repository.api

import androidx.paging.PagingSource
import com.c137.data.model.DataLocation

abstract class LocationPagingSource : PagingSource<Int, DataLocation>()