package com.c137.characters.classical.di

import androidx.annotation.NonNull
import com.c137.characters.domain.GetCharactersUseCase
import com.c137.characters.presentation.CharactersViewModel
import com.c137.characters.presentation.CharactersViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testViewModelModule = module {
    viewModel<CharactersViewModel> { (@NonNull getCharactersUseCase: GetCharactersUseCase) ->
        CharactersViewModelImpl(getCharactersUseCase)
    }
}