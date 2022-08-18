package com.c137.feature.search

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.c137.domain.model.PresentationCharacter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val searchViewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Search() }
    }

    @Composable
    fun Search() {
        Column {
            val id = remember { mutableStateOf(0) }
            val name = remember { mutableStateOf("") }

            SearchTextField(id = id.value, onValueChange = { id.value = it.toIntOrNull() ?: 0 })
            SearchButton(id = id.value)
            SearchOutput(name = name.value)

            LaunchedEffect(Unit) {
                searchViewModel.searchState
                    .collect {
                        if (it is SearchState.Success) {
                            name.value = isDeadOrLive { it.character }
                        } else {
                            name.value = ""
                        }
                    }
            }
        }
    }

    @Composable
    fun SearchTextField(id: Int, onValueChange: (String) -> Unit) {
        OutlinedTextField(
            value = when (id > 0) {
                true -> id.toString()
                false -> ""
            },
            onValueChange = onValueChange,
            label = { Text("Id") }
        )
    }

    @Composable
    fun SearchButton(id: Int) {
        val scope = rememberCoroutineScope()
        Button(onClick = {
            scope.launch {
                searchViewModel.getCharacterById(id)
            }
        }) {
            Text(text = ("Search"))
        }
    }

    @Composable
    fun SearchOutput(name: String) {
        Text(text = name)
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
}