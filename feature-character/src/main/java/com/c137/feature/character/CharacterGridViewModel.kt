package com.c137.feature.character

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.c137.domain.GetCharacterByIdUseCase
import com.c137.domain.GetPagingCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterGridViewModel @Inject constructor(
    private val getPagingCharacterUseCase: GetPagingCharacterUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ViewModel() {

    // examples of using a delegated property.

    var isExpandable by mutableStateOf(true)
        private set

    var searchKeyword by mutableStateOf("")
        private set

    val pagingCharacters by lazy {
        getPagingCharacterUseCase.execute().cachedIn(viewModelScope)
    }

    fun expand() {
        if (isExpandable) {
            isExpandable = false
        }
    }

    fun toggleIsExpandable() {
        isExpandable = !isExpandable
    }

    fun updateSearchKeyword(newValue: String) {
        searchKeyword = newValue
        viewModelScope.launch {

        }
    }
}