package com.c137.feature.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.c137.domain.GetPagingLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationCarouselViewModel @Inject constructor(
    private val getPagingLocationUseCase: GetPagingLocationUseCase
) : ViewModel() {

    val locationCharacters by lazy {
        getPagingLocationUseCase.execute()
            .cachedIn(scope = viewModelScope)
    }
}