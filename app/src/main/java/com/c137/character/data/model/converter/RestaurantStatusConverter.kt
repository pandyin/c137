package com.c137.character.data.model.converter

import androidx.room.TypeConverter
import com.c137.character.data.model.Status

class RestaurantStatusConverter {

    @TypeConverter
    fun fromPriorityToStatus(name: String): Status {
        return Status.fromName(name)
    }

    @TypeConverter
    fun fromStatusToPriority(status: Status): String {
        return status.name
    }
}