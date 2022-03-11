package com.c137.data.model.converter

import androidx.room.TypeConverter
import com.c137.data.model.Status

class CharacterStatusConverter {

    @TypeConverter
    fun fromNameToStatus(name: String): Status {
        return Status.fromName(name)
    }

    @TypeConverter
    fun fromStatusToName(status: Status): String {
        return status.name
    }
}