package com.c137.domain.usecase.api

import com.c137.data.model.Status
import com.c137.domain.usecase.model.CharacterDomainModel
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharactersByStatus(status: Status): Flowable<List<CharacterDomainModel>>

    fun getCharacters(): Flowable<List<CharacterDomainModel>>

    fun getCharacterById(id: Int): Flow<CharacterDomainModel>
}