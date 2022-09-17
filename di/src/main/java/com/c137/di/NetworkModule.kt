package com.c137.di

import android.content.Context
import com.c137.data.datasource.paging.api.CharacterPagingService
import com.c137.data.datasource.paging.api.EpisodePagingService
import com.c137.data.datasource.paging.api.LocationPagingService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun gson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Singleton
    @Provides
    fun retrofit(
        @ApplicationContext context: Context,
        gson: Gson,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(context.getString(R.string.swapi_base_url))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun pagingCharacterService(retrofit: Retrofit): CharacterPagingService {
        return retrofit.create(CharacterPagingService::class.java)
    }

    @Singleton
    @Provides
    fun pagingLocationService(retrofit: Retrofit): LocationPagingService {
        return retrofit.create(LocationPagingService::class.java)
    }

    @Singleton
    @Provides
    fun pagingEpisodeService(retrofit: Retrofit): EpisodePagingService {
        return retrofit.create(EpisodePagingService::class.java)
    }
}