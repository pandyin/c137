package com.c137.feature.search

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val searchViewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        lifecycleScope.launch {
            searchViewModel.getCharacterById(3)
            searchViewModel.searchState.collect {
                if (it is SearchState.Success) {
                    Log.e("testtest", it.character.name)
                }
            }
        }
    }
}