package com.c137.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.c137.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

@Composable
fun LandingPage(onTimeOut: () -> Unit) {
    val newOnTimeOut by rememberUpdatedState(newValue = onTimeOut)
    LaunchedEffect(key1 = Unit) {
        delay(2.seconds.toLong(DurationUnit.MILLISECONDS))
        // always refers to a new onTimeOut
        newOnTimeOut()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.landing_background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_character),
            contentDescription = "",
        )
    }
}

@Preview
@Composable
fun LandingPagePreview() {
    LandingPage { }
}
