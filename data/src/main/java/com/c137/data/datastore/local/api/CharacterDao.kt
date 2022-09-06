package com.c137.data.datastore.local.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.c137.data.model.DataCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("select * from character where characterId = :id")
    fun getCharacterById(id: Int): Flow<DataCharacter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: DataCharacter)
}