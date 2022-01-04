package com.c137.di

import com.c137.characters.presentation.CharactersViewModel
import com.c137.characters.presentation.CharactersViewModelImpl
import io.mockk.mockk
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mockCharactersViewModelModule = module {
    viewModel<CharactersViewModel> {
        val charactersViewModel = mockk<CharactersViewModelImpl>()
        charactersViewModel
    }
}