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
import com.c137.domain.model.PresentationLocation
import com.c137.domain.model.searchKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

private const val NOT_FOUND_INDEX = -1

@HiltViewModel
class CharacterGridViewModel @Inject constructor(
    private val getPagingCharacterUseCase: GetPagingCharacterUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ViewModel() {

    var isExpanded by mutableStateOf(false)
        private set

    private var currentLocations = MutableStateFlow(hashSetOf<PresentationLocation>())
    val locations: StateFlow<HashSet<PresentationLocation>> = currentLocations

    private val currentSearchInput = MutableStateFlow("")
    val searchInput: StateFlow<String> = currentSearchInput

    private val currentScrollingState =
        MutableStateFlow<ScrollingState>(ScrollingState.ScrollTo(index = 0))
    val scrollingState: StateFlow<ScrollingState> = currentScrollingState

    val pagingCharacters by lazy {
        combine(
            getPagingCharacterUseCase.execute().cachedIn(scope = viewModelScope),
            searchInput,
            locations
        ) { paging, searchInput, locations ->
            paging.filter {
                ((searchInput.isEmpty() || isMatched(
                    searchInput = searchInput,
                    searchKeys = it.searchKeys()
                )) && (locations.isEmpty() || isResident(
                    id = it.id,
                    locations = locations
                )))
            }
        }
    }

    private fun isMatched(searchInput: String, searchKeys: MutableList<String>) =
        searchInput.lowercase()
            .trim()
            .split(" ")
            .indexOfFirst { input ->
                searchKeys.indexOfFirst { it.contains(input) } > NOT_FOUND_INDEX
            } > NOT_FOUND_INDEX

    private fun isResident(id: Int, locations: HashSet<PresentationLocation>) =
        locations.indexOfFirst { it.isResident(id) } > NOT_FOUND_INDEX

    fun toggleIsExpanded() {
        isExpanded = !isExpanded
    }

    fun toggleLocation(location: PresentationLocation) {
        val newList = hashSetOf(*currentLocations.value.toTypedArray())
        if (!newList.remove(location)) {
            newList.add(location)
        }
        currentLocations.value = newList
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