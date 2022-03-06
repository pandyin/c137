package com.c137

import android.app.Application
import com.c137.data.repository.datastore.di.koin.databaseDaoKoinModule
import com.c137.data.repository.datastore.di.koin.networkKoinModule
import com.c137.data.repository.datastore.local.di.koin.characterLocalDatastoreKoinModule
import com.c137.data.repository.datastore.remote.di.koin.characterRemoteDatastoreKoinModule
import com.c137.data.repository.di.koin.characterRepositoryKoinModule
import com.c137.di.AppComponent
import com.c137.di.DaggerAppComponent
import com.c137.domain.di.koin.useCaseKoinModule
import com.c137.presentation.di.koin.viewModelKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class C137App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        startDagger()
    }

    private fun startDagger() {
        appComponent = DaggerAppComponent.factory()
            .create(this)
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@C137App)
            modules(
                listOf(
                    networkKoinModule(),
                    databaseDaoKoinModule,
                    characterLocalDatastoreKoinModule,
                    characterRemoteDatastoreKoinModule,
                    characterRepositoryKoinModule,
                    useCaseKoinModule,
                    viewModelKoinModule
                )
            )
        }
    }
}