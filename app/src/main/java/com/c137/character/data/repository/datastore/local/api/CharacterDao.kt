package com.c137.character.data.repository.datastore.local.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.c137.character.data.model.Character
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<Character>): Completable

    @Query("select * from character")
    fun getCharacters(): Flowable<List<Character>>

    @Query("select * from character")
    fun getCharacter(): List<Character>
}