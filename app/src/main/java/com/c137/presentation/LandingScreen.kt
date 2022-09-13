package com.c137.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BackHand
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
    LandingPage { }
}

@Composable
fun CharacterCarousel() {
    LazyRow {
        item {
            Surface {
                Image(imageVector = Icons.Outlined.BackHand, contentDescription = "")
            }
        }
    }
}

@Preview
@Composable
fun CharacterCarouselPreview() {
    CharacterCarousel()
}
