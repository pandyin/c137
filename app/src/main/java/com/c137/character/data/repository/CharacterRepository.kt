package com.c137.character.data.repository

import com.c137.character.data.model.Character
import com.c137.character.data.model.LocationWithResidents
import com.c137.character.data.model.Status
import io.reactivex.rxjava3.core.Flowable

interface CharacterRepository {

    fun getCharactersByStatus(page: Int, status: Status): Flowable<List<Character>>

    fun getCharacters(page: Int): Flowable<List<Character>>

    fun getCharacterById(id: Int): Flowable<Character>

    fun getLocations(page: Int): Flowable<List<LocationWithResidents>>
}