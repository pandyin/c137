package com.c137.feature.character

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.ui.platform.ComposeView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListActivity : AppCompatActivity() {

    private val searchViewModel by viewModels<CharacterListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ComposeView(this).apply {
            setContent {
                Scaffold(topBar = {
                    TopAppBar(title = {}, navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Rounded.Face, contentDescription = "")
                        }
                    })
                }) {

                }
            }
        })
    }
}