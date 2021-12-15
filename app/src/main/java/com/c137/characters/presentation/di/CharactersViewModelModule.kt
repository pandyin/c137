package com.c137.characters.presentation.di

import com.c137.characters.presentation.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<CharactersViewModel> {
        CharactersViewModel(get())
    }
}