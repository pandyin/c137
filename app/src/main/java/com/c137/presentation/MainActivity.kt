package com.c137.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.c137.R
import com.c137.feature.search.Response
import com.c137.feature.search.SearchViewModel
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : RxAppCompatActivity() {

    private val searchViewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        subscribeToListOfCharacters()
    }

    private fun subscribeToListOfCharacters() {
        searchViewModel.getCharacterById(3)
            .doOnNext {
                when (it is Response.Error) {
                    true -> {
                        val errorMessage = it.errorMessage ?: getString(R.string.unknown_error)
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                    false -> {

                    }
                }
            }.subscribe()
    }
}