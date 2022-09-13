package com.c137.feature.location

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LocationCarousel(
    highlighted: List<String> = emptyList(),
    viewModel: LocationCarouselViewModel = hiltViewModel()
) {
    LazyRow {
        for (i in 1..100) {
            item {
                Surface {

                }
            }
        }
    }
}

@Preview
@Composable
fun CharacterCarouselPreview() {
    LocationCarousel()
}