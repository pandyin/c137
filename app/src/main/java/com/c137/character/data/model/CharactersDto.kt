package com.c137.character.data.model

import com.google.gson.annotations.SerializedName

data class CharactersDto(@SerializedName("results") val characters: List<Character>)