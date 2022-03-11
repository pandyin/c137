package com.c137.presentation.di.koin

import com.c137.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelKoinModule = module {
    viewModel<MainViewModel> {
        MainViewModel(get(), get(), get())
    }
}