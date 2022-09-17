package com.c137.data.model.converter

import androidx.room.TypeConverter

class ListOfIntsConverter {

    @TypeConverter
    fun fromStringToList(string: String) = string.split(",").map { it.trim().toInt() }

    @TypeConverter
    fun fromListToString(list: List<Int>) = list.joinToString()
}