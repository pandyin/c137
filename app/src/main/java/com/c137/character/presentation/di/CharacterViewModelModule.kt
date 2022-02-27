package com.c137.character.presentation.di

import com.c137.character.presentation.CharacterViewModel
import com.c137.character.presentation.CharacterViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<CharacterViewModel> {
        CharacterViewModelImpl(get(), get(), get())
    }
}