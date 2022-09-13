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
import com.c137.domain.model.searchKeys
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

    var isExpanded by mutableStateOf(false)
        private set

    private var currentLocations = MutableStateFlow(mutableListOf<String>())
    val locations: StateFlow<List<String>> = currentLocations

    private val currentSearchInput = MutableStateFlow("")
    val searchInput: StateFlow<String> = currentSearchInput

    private val currentScrollingState = MutableStateFlow<ScrollingState>(ScrollingState.ScrollTo(index = 0))
    val scrollingState: StateFlow<ScrollingState> = currentScrollingState

    val pagingCharacters by lazy {
        getPagingCharacterUseCase.execute()
            .cachedIn(scope = viewModelScope)
            .combine(flow = searchInput) { paging, input ->
                if (input.isEmpty()) {
                    paging
                } else {
                    val notFoundIndex = -1
                    paging.filter {
                        when (input.lowercase().trim().split(" ").indexOfFirst { input ->
                            it.searchKeys().indexOfFirst { key ->
                                key.contains(input)
                            } > notFoundIndex
                        }) {
                            notFoundIndex -> false
                            else -> true
                        }
                    }
                }
            }
    }

    fun toggleIsExpanded() {
        isExpanded = !isExpanded
    }

    fun addLocation(newLocation: String) {
        currentLocations.value.apply {
            add(newLocation)
        }
    }

    fun updateSearchInput(newValue: String) {
        currentSearchInput.value = newValue
    }

    fun expand(index: Int, character: PresentationCharacter) {
        if (!isExpanded) {
            isExpanded = true
        }
        currentScrollingState.value = ScrollingState.ScrollTo(index = index)
    }
}

sealed class ScrollingState {
    class ScrollTo(val index: Int) : ScrollingState()
}