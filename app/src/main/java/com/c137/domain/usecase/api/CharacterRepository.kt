package com.c137.domain.usecase.api

import com.c137.data.model.Status
import com.c137.domain.usecase.model.CharacterDomain
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharactersByStatus(status: Status): Flowable<List<CharacterDomain>>

    fun getCharacters(): Flowable<List<CharacterDomain>>

    fun getCharacterById(id: Int): Flow<CharacterDomain>
}