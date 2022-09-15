package com.c137.data.datasource.paging.api

import com.c137.data.model.dto.LocationDto
import com.c137.data.model.dto.ResultsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationPagingService {

    @GET("location")
    suspend fun getLocationsByPage(@Query("page") page: Int): ResultsDto<LocationDto>
}