package com.c137.characters.common

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ResultConverter {

    companion object {

        fun <T> convert(jsonObject: JsonObject?): List<T> {
            return when (jsonObject?.has("results") ?: false) {
                true -> {
                    val results = jsonObject!!["results"].asJsonArray
                    val type: Type = object : TypeToken<List<T>>() {}.type
                    Gson().fromJson(results, type)
                }
                false -> emptyList()
            }
        }
    }
}