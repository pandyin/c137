package com.c137.characters.presentation.di

import com.c137.characters.presentation.CharactersViewModel
import com.c137.characters.presentation.CharactersViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<CharactersViewModel> {
        CharactersViewModelImpl()
    }
}