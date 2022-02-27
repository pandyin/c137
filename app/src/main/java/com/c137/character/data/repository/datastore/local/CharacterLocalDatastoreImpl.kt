package com.c137.character.data.repository.datastore.local

import com.c137.character.data.model.Character
import com.c137.character.data.model.Location
import com.c137.character.data.model.LocationWithResidents
import com.c137.character.data.model.Status
import com.c137.character.data.repository.datastore.local.api.CharacterDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class CharacterLocalDatastoreImpl(private val characterDao: CharacterDao) :
    CharacterLocalDatastore {

    override fun insertCharacter(character: Character): Completable {
        return characterDao.insertCharacter(character)
    }

    override fun insertCharacters(characters: List<Character>): Completable {
        return characterDao.insertCharacters(characters)
    }

    override fun getCharactersByStatus(status: Status): Flowable<List<Character>> {
        return characterDao.getCharactersByStatus(status)
    }

    override fun getCharacters(): Flowable<List<Character>> {
        return characterDao.getCharacters()
    }

    override fun getCharacterById(id: Int): Flowable<Character> {
        return characterDao.getCharacterById(id)
    }

    override fun insertLocations(locations: List<Location>): Completable {
        return characterDao.insertLocations(locations)
    }

    override fun getLocations(): Flowable<List<LocationWithResidents>> {
        return characterDao.getLocations()
    }
}