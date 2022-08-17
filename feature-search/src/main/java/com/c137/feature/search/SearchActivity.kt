package com.c137.feature.search

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import com.c137.domain.model.PresentationCharacter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val searchViewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SearchButton() }
    }

    @Composable
    fun SearchButton() {
    }

    // an example of using inline usage.
    private inline fun isDeadOrLive(block: () -> PresentationCharacter): String {
        // an example of using destructuring usage.
        val (name, isDead) = block()
        return "Copy this text to a caller: " + when (isDead) {
            true -> "$name is dead."
            else -> "$name is NOT dead."
        }
    }

    override fun onStart() {
        super.onStart()

        lifecycleScope.launch {
            searchViewModel.getCharacterById(3)
            searchViewModel.searchState.collect {
                if (it is SearchState.Success) {
                    isDeadOrLive { it.character }
                }
            }
        }
    }
}