package com.c137.data.datastore.remote

import com.c137.data.model.dto.LocationDto
import com.c137.data.model.dto.ResultsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {

    @GET("location")
    fun getLocation(@Query("page") page: Int): Call<ResultsDto<LocationDto>>
}