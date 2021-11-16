package co.swapi.starships.presentation.di

import co.swapi.starships.presentation.StarshipsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<StarshipsViewModel> {
        StarshipsViewModel(get())
    }
}