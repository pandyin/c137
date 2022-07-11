package com.c137.android.presentation.doubles

import com.c137.data.model.CharacterDataModel
import com.c137.data.model.Status
import com.c137.data.datastore.local.api.CharacterDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

class FakeCharacterDao : CharacterDao {

    override fun getCharactersByStatus(status: Status): Flowable<List<CharacterDataModel>> {
        TODO("Not yet implemented")
    }

    override fun getCharacters(): Flowable<List<CharacterDataModel>> {
        return Flowable.empty()
    }

    override fun insertCharacters(characters: List<CharacterDataModel>): Completable {
        return Completable.complete()
    }

    override fun getCharacterById(id: Int): Flow<CharacterDataModel> {
        TODO("Not yet implemented")
    }

    override suspend fun insertCharacter(character: CharacterDataModel) {
        TODO("Not yet implemented")
    }
}