package com.c137.feature.character

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import com.c137.domain.GetCharacterByIdUseCase
import com.c137.domain.GetPagingCharacterUseCase
import com.c137.domain.model.PresentationCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class CharacterGridViewModel @Inject constructor(
    private val getPagingCharacterUseCase: GetPagingCharacterUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ViewModel() {

    var isExpandable by mutableStateOf(true)
        private set

    private val currentSearchInput = MutableStateFlow("")
    val searchInput: StateFlow<String> = currentSearchInput

    private val currentScrollingState =
        MutableStateFlow<ScrollingState>(ScrollingState.ScrollTo(index = 0))

    val scrollingState: StateFlow<ScrollingState> = currentScrollingState

    val pagingCharacters by lazy {
        getPagingCharacterUseCase.execute()
            .cachedIn(viewModelScope)
            .combine(searchInput) { paging, input ->
                if (input.isEmpty()) {
                    paging
                } else {
                    val notFoundIndex = -1
                    paging.filter {
                        val keys = it.name.lowercase().trim().split(" ").toMutableList()
                        keys.add(it.species.lowercase())

                        when (input.lowercase().trim().split(" ").indexOfFirst { input ->
                            keys.indexOfFirst { key -> key.contains(input) } > notFoundIndex
                        }) {
                            notFoundIndex -> false
                            else -> true
                        }
                    }
                }
            }
    }

    fun toggleIsExpandable() {
        isExpandable = !isExpandable
    }

    fun updateSearchInput(newValue: String) {
        currentSearchInput.value = newValue
    }

    fun expand(index: Int, character: PresentationCharacter) {
        if (isExpandable) {
            isExpandable = false
        }
        currentScrollingState.value = ScrollingState.ScrollTo(index = index)
    }
}

sealed class ScrollingState {
    class ScrollTo(val index: Int) : ScrollingState()
}