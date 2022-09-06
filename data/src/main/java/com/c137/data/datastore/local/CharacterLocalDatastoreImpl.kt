package com.c137.data.datastore.local

import com.c137.data.datastore.local.api.CharacterDao
import com.c137.data.model.DataCharacter
import com.c137.data.repository.api.CharacterLocalDatastore
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class CharacterLocalDatastoreImpl @Inject constructor(private val dao: CharacterDao) :
    CharacterLocalDatastore {

    override fun getCharacterById(id: Int): Flow<DataCharacter> {
        return dao.getCharacterById(id)
    }

    override suspend fun insertCharacter(character: DataCharacter) {
        return dao.insertCharacter(character)
    }
}