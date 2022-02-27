package com.c137.character.presentation

import androidx.lifecycle.ViewModel
import com.c137.character.data.model.Character
import com.c137.character.data.model.LocationWithResidents
import com.c137.character.data.model.Status
import io.reactivex.rxjava3.core.Flowable

abstract class CharacterViewModel : ViewModel() {

    abstract fun getCharactersByStatus(page: Int, status: Status): Flowable<List<Character>>

    abstract fun getCharacters(page: Int): Flowable<List<Character>>

    abstract fun getCharacterById(id: Int): Flowable<Character>

    abstract fun getLocation(page: Int): Flowable<List<LocationWithResidents>>
}