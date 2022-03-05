package com.c137.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
class ContextModule(private val context: Context) {

    @Provides
    fun context(): Context {
        return context.applicationContext
    }
}