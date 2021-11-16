package co.swapi.starships.data.repository.di

import co.swapi.starships.data.repository.StarshipsRepository
import co.swapi.starships.data.repository.StarshipsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<StarshipsRepository> {
        StarshipsRepositoryImpl(get())
    }
}