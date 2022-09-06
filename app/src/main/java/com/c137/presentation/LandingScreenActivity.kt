package com.c137.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.c137.R
import com.c137.feature.character.CharacterGridActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

@AndroidEntryPoint
class LandingScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LandingPage {
                startActivity(Intent(this, CharacterGridActivity::class.java))
                finish()
            }
        }
    }
}

@Composable
fun LandingPage(onTimeOut: () -> Unit) {
    val newOnTimeOut by rememberUpdatedState(newValue = onTimeOut)
    LaunchedEffect(key1 = Unit) {
        delay(2.seconds.toLong(DurationUnit.MILLISECONDS))
        // always refers to a new onTimeOut
        newOnTimeOut()
    }

    Image(
        painter = painterResource(id = R.drawable.landing_foreground),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillHeight
    )
}

@Preview
@Composable
fun LandingPagePreview() {
    LandingPage {}
}
