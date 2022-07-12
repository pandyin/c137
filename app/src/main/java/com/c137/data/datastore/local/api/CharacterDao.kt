package com.c137.data.datastore.local.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.c137.data.model.CharacterData
import com.c137.data.model.CharacterStatus
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("select * from character where status = :status")
    fun getCharactersByStatus(status: CharacterStatus): Flowable<List<CharacterData>>

    @Query("select * from character")
    fun getCharacters(): Flowable<List<CharacterData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<CharacterData>): Completable

    @Query("select * from character where characterId = :id")
    fun getCharacterById(id: Int): Flow<CharacterData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterData)
}