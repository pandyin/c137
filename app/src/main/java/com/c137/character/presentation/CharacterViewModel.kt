package com.c137.character.presentation

import androidx.lifecycle.ViewModel
import com.c137.character.data.model.Character
import com.c137.character.data.model.Status
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

abstract class CharacterViewModel : ViewModel() {

    abstract fun getCharactersByStatus(page: Int, status: Status): Single<List<Character>>

    abstract fun getCharacters(page: Int): Single<List<Character>>

    abstract fun getCharacterById(id: Int): Maybe<Character>
}