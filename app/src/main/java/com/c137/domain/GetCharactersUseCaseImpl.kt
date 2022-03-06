package com.c137.domain

import com.c137.data.model.Character
import com.c137.data.repository.CharacterRepository
import com.c137.di.ActivityScope
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

@ActivityScope
class GetCharactersUseCaseImpl @Inject constructor(private val repository: CharacterRepository) :
    GetCharactersUseCase {

    override fun execute(page: Int): Flowable<List<Character>> {
        return repository.getCharacters(page)
    }
}