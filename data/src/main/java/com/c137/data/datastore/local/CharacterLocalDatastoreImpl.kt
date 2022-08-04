package com.c137.data.datastore.local

import com.c137.data.datastore.local.api.CharacterDao
import com.c137.data.model.DataCharacter
import com.c137.data.model.CharacterStatus
import com.c137.data.repository.api.CharacterLocalDatastore
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class CharacterLocalDatastoreImpl @Inject constructor(private val dao: CharacterDao) :
    CharacterLocalDatastore {

    override fun getAliveCharacters(): Flowable<List<DataCharacter>> {
        return dao.getCharactersByStatus(CharacterStatus.Alive)
    }

    override fun getDeadCharacters(): Flowable<List<DataCharacter>> {
        return dao.getCharactersByStatus(CharacterStatus.Dead)
    }

    override fun getCharacters(): Flowable<List<DataCharacter>> {
        return dao.getCharacters()
    }

    override fun insertCharacters(characters: List<DataCharacter>): Completable {
        return dao.insertCharacters(characters)
    }

    override fun getCharacterById(id: Int): Flow<DataCharacter> {
        return dao.getCharacterById(id)
    }

    override suspend fun insertCharacter(character: DataCharacter) {
        return dao.insertCharacter(character)
    }
}