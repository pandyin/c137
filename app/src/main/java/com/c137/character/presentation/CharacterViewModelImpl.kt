package com.c137.character.presentation

import com.c137.character.data.model.Character
import com.c137.character.data.model.LocationWithResidents
import com.c137.character.data.model.Status
import com.c137.character.domain.GetCharacterByIdUseCase
import com.c137.character.domain.GetCharactersByStatusUseCase
import com.c137.character.domain.GetCharactersUseCase
import com.c137.character.domain.GetLocationUseCase
import io.reactivex.rxjava3.core.Flowable

class CharacterViewModelImpl(
    private val getCharactersByStatusUseCase: GetCharactersByStatusUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getLocationUseCase: GetLocationUseCase,
) : CharacterViewModel() {

    override fun getCharactersByStatus(page: Int, status: Status): Flowable<List<Character>> {
        return getCharactersByStatusUseCase.execute(page, status)
    }

    override fun getCharacters(page: Int): Flowable<List<Character>> {
        return getCharactersUseCase.execute(page)
    }

    override fun getCharacterById(id: Int): Flowable<Character> {
        return getCharacterByIdUseCase.execute(id)
    }

    override fun getLocation(page: Int): Flowable<List<LocationWithResidents>> {
        return getLocationUseCase.execute(page)
    }
}