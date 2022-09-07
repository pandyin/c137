package com.c137.feature.character

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterGridActivity : AppCompatActivity() {

    private val viewModel by viewModels<CharacterGridViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ComposeView(this).apply {
            setContent { CharacterScaffold() }
        })

        lifecycleScope.launch {
            viewModel.scrollingState
                .collect {

                }
        }
    }
}