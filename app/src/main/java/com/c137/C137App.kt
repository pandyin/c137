package com.c137

import android.app.Application
import com.c137.characters.data.repository.datastore.di.*
import com.c137.characters.data.repository.di.repositoryModule
import com.c137.characters.domain.di.useCaseModule
import com.c137.characters.presentation.di.viewModelModule
import io.socket.client.Socket.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class C137App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@C137App)
            modules(
                listOf(
                    networkModule,
                    databaseDaoModule,
                    localDatastoreModule,
                    remoteDatastoreModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}