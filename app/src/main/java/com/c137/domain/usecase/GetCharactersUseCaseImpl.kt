package com.c137.domain.usecase

import com.c137.data.model.Character
import com.c137.domain.CharacterRepository
import com.c137.presentation.GetCharactersUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

@ViewModelScoped
class GetCharactersUseCaseImpl @Inject constructor(private val repository: CharacterRepository) :
    GetCharactersUseCase {

    override fun execute(): Flowable<List<Character>> {
        return repository.getCharacters()
    }
}