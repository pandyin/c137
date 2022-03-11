package com.c137.data.repository.datastore.local

import com.c137.data.model.Character
import com.c137.data.model.Status
import com.c137.data.repository.datastore.local.api.CharacterDao
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class CharacterLocalDatastoreImpl @Inject constructor(private val dao: CharacterDao) :
    CharacterLocalDatastore {

    override fun getCharactersByStatus(status: Status): Flowable<List<Character>> {
        return dao.getCharactersByStatus(status)
    }

    override fun getCharacters(): Flowable<List<Character>> {
        return dao.getCharacters()
    }

    override fun insertCharacters(characters: List<Character>): Completable {
        return dao.insertCharacters(characters)
    }

    override fun getCharacterById(id: Int): Flow<Character> {
        return dao.getCharacterById(id)
    }

    override suspend fun insertCharacter(character: Character) {
        return dao.insertCharacter(character)
    }
}