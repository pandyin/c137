package com.c137.feature.character

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterGridViewModel @Inject constructor() : ViewModel() {

    // examples of using a delegated property.

    var isExpandable by mutableStateOf(true)
        private set

    var searchKeyword by mutableStateOf("")
        private set

    fun toggleIsExpandable() {
        isExpandable = !isExpandable
    }

    fun updateSearchKeyword(newValue: String) {
        searchKeyword = newValue
        viewModelScope.launch {

        }
    }
}