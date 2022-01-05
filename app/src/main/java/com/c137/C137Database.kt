package com.c137

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.c137.characters.data.model.Character
import com.c137.characters.data.repository.datastore.local.api.CharactersDao

@Database(version = 1, entities = [Character::class])
abstract class C137Database : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao

    companion object {

        @Volatile
        private var instance: C137Database? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, C137Database::class.java, "c137.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}