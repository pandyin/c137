package com.c137.character.data.repository.datastore.remote

import com.c137.character.data.model.Status
import com.c137.character.data.model.dto.CharacterDto
import com.c137.character.data.model.dto.LocationDto
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface CharacterRemoteDatastore {

    fun getCharactersByStatus(page: Int, status: Status): Single<List<CharacterDto>>

    fun getCharacters(page: Int): Single<List<CharacterDto>>

    fun getCharacterById(id: Int): Maybe<CharacterDto>

    fun getLocations(page: Int): Single<List<LocationDto>>
}