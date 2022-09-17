package com.c137.feature.episode

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.c137.domain.model.PresentationEpisode

@Composable
fun EpisodeCarousel(
    viewModel: EpisodeCarouselViewModel = hiltViewModel(),
    episodes: List<PresentationEpisode>,
    onClick: (PresentationEpisode) -> Unit
) {
    val state = viewModel.episodes.collectAsState(initial = emptyList())
    Column {
        LazyRow(modifier = Modifier.padding(start = 3.dp, end = 3.dp)) {
            items(state.value) {
                EpisodeItem(
                    selected = episodes.contains(it),
                    name = it.name,
                    onClick = { onClick(it) }
                )
            }
        }
    }
}

@Composable
private fun EpisodeItem(selected: Boolean, name: String, onClick: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Box(
            modifier = Modifier
                .clickable { onClick() }
                .padding(start = 3.dp, end = 3.dp, bottom = 6.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(color = MaterialTheme.colors.surface)
        ) {
            Text(
                text = name,
                modifier = Modifier.padding(paddingValues = PaddingValues(6.dp)),
                color = when (selected) {
                    true -> MaterialTheme.colors.onSurface
                    false -> MaterialTheme.colors.onBackground
                },
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}