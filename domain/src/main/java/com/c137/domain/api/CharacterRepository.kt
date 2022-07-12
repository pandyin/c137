package com.c137.domain.api

import com.c137.domain.model.CharacterDomain
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getAliveCharacters(): Flowable<List<CharacterDomain>>

    fun getDeadCharacters(): Flowable<List<CharacterDomain>>

    fun getCharacters(): Flowable<List<CharacterDomain>>

    fun getCharacterById(id: Int): Flow<CharacterDomain>
}