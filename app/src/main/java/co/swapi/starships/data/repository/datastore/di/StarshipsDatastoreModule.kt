package co.swapi.starships.data.repository.datastore.di

import co.swapi.starships.data.repository.datastore.remote.StarshipsRemoteRepository
import co.swapi.starships.data.repository.datastore.remote.StarshipsRemoteRepositoryImpl
import org.koin.dsl.module

val datastoreModule = module {
    factory<StarshipsRemoteRepository> {
        StarshipsRemoteRepositoryImpl(get())
    }
}