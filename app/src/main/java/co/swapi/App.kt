package co.swapi

import android.app.Application
import co.swapi.di.networkModule
import co.swapi.starships.data.repository.datastore.di.datastoreModule
import co.swapi.starships.data.repository.di.repositoryModule
import co.swapi.starships.domain.di.useCaseModule
import co.swapi.starships.presentation.di.viewModelModule
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