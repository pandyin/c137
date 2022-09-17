package com.c137.feature.character

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import com.c137.domain.GetPagingCharacterUseCase
import com.c137.domain.model.PresentationCharacter
import com.c137.domain.model.PresentationEpisode
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
    private val getPagingCharacterUseCase: GetPagingCharacterUseCase
) : ViewModel() {

    var isExpanded by mutableStateOf(false)
        private set

    private var currentLocations = MutableStateFlow(listOf<PresentationLocation>())
    val locations: StateFlow<List<PresentationLocation>> = currentLocations

    private var currentEpisodes = MutableStateFlow(listOf<PresentationEpisode>())
    val episodes: StateFlow<List<PresentationEpisode>> = currentEpisodes

    private val currentSearchInput = MutableStateFlow("")
    val searchInput: StateFlow<String> = currentSearchInput

    private val currentScrollingState =
        MutableStateFlow<ScrollingState>(ScrollingState.ScrollTo(index = 0))
    val scrollingState: StateFlow<ScrollingState> = currentScrollingState

    val pagingCharacters by lazy {
        combine(
            getPagingCharacterUseCase.execute().cachedIn(scope = viewModelScope),
            searchInput,
            locations,
            episodes
        ) { paging, searchInput, locations, episodes ->
            paging.filter {
                ((searchInput.isEmpty() || isMatched(
                    searchInput = searchInput,
                    searchKeys = it.searchKeys()
                )) && (locations.isEmpty() || beenToLocations(
                    id = it.id,
                    origin = it.origin.id,
                    lastKnown = it.lastKnown.id,
                    locations = locations
                )) && (episodes.isEmpty() || isPartOfEpisodes(
                    id = it.id,
                    episodes = episodes
                )))
            }
        }
    }

    private fun isMatched(searchInput: String, searchKeys: List<String>) =
        searchInput.lowercase()
            .trim()
            .split(" ")
            .indexOfFirst { input ->
                searchKeys.indexOfFirst { it.contains(input) } > NOT_FOUND_INDEX
            } > NOT_FOUND_INDEX

    private fun beenToLocations(
        id: Int,
        origin: Int,
        lastKnown: Int,
        locations: List<PresentationLocation>
    ) = locations.indexOfFirst {
        it.isResident(id)
                || it.id == origin
                || it.id == lastKnown
    } > NOT_FOUND_INDEX

    private fun isPartOfEpisodes(id: Int, episodes: List<PresentationEpisode>) =
        episodes.indexOfFirst { it.characters.contains(id) } > NOT_FOUND_INDEX

    fun toggleIsExpanded() {
        isExpanded = !isExpanded
    }

    fun toggleLocation(location: PresentationLocation) {
        val newList = mutableListOf(*currentLocations.value.toTypedArray())
        if (!newList.remove(location)) {
            newList.add(location)
        }
        currentLocations.value = newList
    }

    fun toggleEpisode(episode: PresentationEpisode) {
        val newList = mutableListOf(*currentEpisodes.value.toTypedArray())
        if (!newList.remove(episode)) {
            newList.add(episode)
        }
        currentEpisodes.value = newList
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