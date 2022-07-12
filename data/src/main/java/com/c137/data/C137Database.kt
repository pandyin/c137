package com.c137.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.c137.data.datastore.local.api.CharacterDao
import com.c137.data.model.CharacterData
import com.c137.data.model.converter.CharacterStatusConverter

@Database(
    version = 1,
    entities = [CharacterData::class]
)
@TypeConverters(CharacterStatusConverter::class)
abstract class C137Database : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

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