package com.c137.character.data.repository.datastore.local

import com.c137.character.data.model.Character
import com.c137.character.data.model.Location
import com.c137.character.data.model.LocationWithResidents
import com.c137.character.data.model.Status
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface CharacterLocalDatastore {

    fun insertCharacter(character: Character): Completable

    fun insertCharacters(characters: List<Character>): Completable

    fun getCharactersByStatus(status: Status): Flowable<List<Character>>

    fun getCharacters(): Flowable<List<Character>>

    fun getCharacterById(id: Int): Flowable<Character>

    fun insertLocations(locations: List<Location>): Completable

    fun getLocations(): Flowable<List<LocationWithResidents>>
}