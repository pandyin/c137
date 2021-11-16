package co.swapi.starships.domain.di

import co.swapi.starships.domain.GetAllStarshipsUseCase
import co.swapi.starships.domain.GetAllStarshipsUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetAllStarshipsUseCase> {
        GetAllStarshipsUseCaseImpl(get())
    }
}