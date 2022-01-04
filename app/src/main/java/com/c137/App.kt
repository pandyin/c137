package com.c137

import android.app.Application
import com.c137.characters.data.repository.datastore.di.datastoreModule
import com.c137.characters.data.repository.di.repositoryModule
import com.c137.characters.domain.di.useCaseModule
import com.c137.characters.presentation.di.viewModelModule
import com.c137.characters.data.repository.datastore.di.networkModule
import io.socket.client.Socket.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    datastoreModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}