package com.c137.data.repository.datastore.local.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.c137.data.model.Character
import com.c137.data.model.Status
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: Character): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<Character>): Completable

    @Query("select * from character where status = :status")
    fun getCharactersByStatus(status: Status): Flowable<List<Character>>

    @Query("select * from character")
    fun getCharacters(): Flowable<List<Character>>

    @Query("select * from character where characterId = :id")
    fun getCharacterById(id: Int): Flowable<Character>
}