package com.c137.data.model.converter

import androidx.room.TypeConverter
import com.c137.common.model.CharacterStatus

class CharacterStatusConverter {

    @TypeConverter
    fun fromNameToStatus(name: String): CharacterStatus {
        return CharacterStatus.fromName(name)
    }

    @TypeConverter
    fun fromStatusToName(status: CharacterStatus): String {
        return status.name
    }
}