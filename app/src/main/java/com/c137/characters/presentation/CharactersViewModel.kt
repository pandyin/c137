package com.c137.characters.presentation

import androidx.lifecycle.ViewModel
import com.c137.characters.data.model.Character
import io.reactivex.rxjava3.core.Single

abstract class CharactersViewModel : ViewModel() {

    abstract fun getCharacters(): Single<List<Character>>
}