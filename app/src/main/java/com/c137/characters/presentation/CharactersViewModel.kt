package com.c137.characters.presentation

import androidx.lifecycle.ViewModel
import com.c137.characters.data.model.Character
import com.c137.characters.data.model.Status
import io.reactivex.rxjava3.core.Single

abstract class CharactersViewModel : ViewModel() {

    abstract fun getCharactersByStatus(status: Status): Single<List<Character>>

    abstract fun getCharacters(): Single<List<Character>>
}