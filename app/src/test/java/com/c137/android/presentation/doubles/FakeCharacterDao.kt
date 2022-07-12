package com.c137.android.presentation.doubles

import com.c137.data.model.CharacterData
import com.c137.data.model.CharacterStatus
import com.c137.data.datastore.local.api.CharacterDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

class FakeCharacterDao : CharacterDao {

    override fun getCharactersByStatus(status: CharacterStatus): Flowable<List<CharacterData>> {
        TODO("Not yet implemented")
    }

    override fun getCharacters(): Flowable<List<CharacterData>> {
        return Flowable.empty()
    }

    override fun insertCharacters(characters: List<CharacterData>): Completable {
        return Completable.complete()
    }

    override fun getCharacterById(id: Int): Flow<CharacterData> {
        TODO("Not yet implemented")
    }

    override suspend fun insertCharacter(character: CharacterData) {
        TODO("Not yet implemented")
    }
}