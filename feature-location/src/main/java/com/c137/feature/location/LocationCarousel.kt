package com.c137.feature.location

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.c137.common.darkColors
import com.c137.domain.model.PresentationLocation

@Composable
fun LocationCarousel(
    viewModel: LocationCarouselViewModel = hiltViewModel(),
    locations: List<PresentationLocation>,
    onClick: (PresentationLocation) -> Unit
) {
    val lazyPaging = viewModel.locations.collectAsLazyPagingItems()
    Column {
        LazyRow {
            itemsIndexed(lazyPaging) { _, location ->
                LocationItem(
                    selected = locations.contains(location),
                    name = location!!.name,
                    onClick = { onClick(location) }
                )
            }
        }
    }
}

@Composable
private fun LocationItem(selected: Boolean, name: String, onClick: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Box(
            modifier = Modifier
                .clickable { onClick() }
                .padding(horizontal = 4.dp, vertical = 4.dp)
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
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun CharacterCarouselPreview() {
    MaterialTheme(colors = darkColors) {

    }
}