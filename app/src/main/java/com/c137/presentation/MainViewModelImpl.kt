package com.c137.presentation

import com.c137.data.model.Character
import com.c137.data.model.Status
import com.c137.domain.GetCharacterByIdUseCase
import com.c137.domain.GetCharactersByStatusUseCase
import com.c137.domain.GetCharactersUseCase
import io.reactivex.rxjava3.core.Flowable

class MainViewModelImpl(
    private val getCharactersByStatusUseCase: GetCharactersByStatusUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : MainViewModel() {

    override fun getCharactersByStatus(page: Int, status: Status): Flowable<List<Character>> {
        return getCharactersByStatusUseCase.execute(page, status)
    }

    override fun getCharacters(page: Int): Flowable<List<Character>> {
        return getCharactersUseCase.execute(page)
    }

    override fun getCharacterById(id: Int): Flowable<Character> {
        return getCharacterByIdUseCase.execute(id)
    }
}