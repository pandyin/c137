package com.c137.data.model.converter

import androidx.room.TypeConverter

class ListOfIntsConverter {

    @TypeConverter
    fun fromStringToList(string: String): List<Int> {
        return string.split(",").map { it.toInt() }
    }

    @TypeConverter
    fun fromListToString(list: List<Int>): String {
        return list.joinToString()
    }
}