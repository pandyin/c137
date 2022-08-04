package com.c137.domain.api

import com.c137.domain.model.DomainCharacter
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getAliveCharacters(): Flowable<List<DomainCharacter>>

    fun getDeadCharacters(): Flowable<List<DomainCharacter>>

    fun getCharacters(): Flowable<List<DomainCharacter>>

    fun getCharacterById(id: Int): Flow<DomainCharacter>
}