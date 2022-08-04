package com.c137.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c137.domain.GetCharacterByIdUseCase
import com.c137.domain.model.PresentationCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getCharacterByIdUseCase: GetCharacterByIdUseCase) :
    ViewModel() {

    val searchState = MutableSharedFlow<SearchState>()

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            getCharacterByIdUseCase.execute(id = id)
                .collect { searchState.emit(SearchState.Success(it)) }
        }
    }
}

sealed class SearchState {
    data class Success(val character: PresentationCharacter) : SearchState()
}