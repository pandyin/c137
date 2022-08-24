package com.c137.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import com.c137.feature.character.CharacterDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class LandingScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LandingComposable {
                startActivity(Intent(this, CharacterDetailActivity::class.java))
            }
        }
    }

    @Composable
    fun LandingComposable(onTimeOut: () -> Unit) {
        val newOnTimeOut by rememberUpdatedState(newValue = onTimeOut)
        LaunchedEffect(key1 = Unit) {
            delay(2000)
            // always refers to a new onTimeOut
            newOnTimeOut()
        }
    }
}