package co.swapi

import android.app.Application
import co.swapi.di.appModule
import co.swapi.starships.data.repository.di.repositoryModule
import co.swapi.starships.domain.di.useCaseModule
import co.swapi.starships.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}