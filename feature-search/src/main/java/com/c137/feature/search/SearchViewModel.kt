package com.c137.feature.search

import androidx.lifecycle.ViewModel
import com.c137.domain.GetCharacterByIdUseCase
import com.c137.domain.model.PresentationCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getCharacterByIdUseCase: GetCharacterByIdUseCase) :
    ViewModel() {

    fun getCharacterById(id: Int): Flow<PresentationCharacter> {
        return getCharacterByIdUseCase.execute(id = id)
    }
}