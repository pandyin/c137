package com.c137.feature.location

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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

@Composable
fun LocationCarousel(
    viewModel: LocationCarouselViewModel = hiltViewModel(),
    locations: HashSet<Int>,
    onClick: (Int) -> Unit
) {
    val lazyPaging = viewModel.locationCharacters.collectAsLazyPagingItems()
    LazyRow {
        itemsIndexed(lazyPaging) { _, location ->
            LocationItem(
                selected = locations.contains(location!!.id),
                id = location.id,
                name = location.name,
                onClick = onClick
            )
        }
    }
}

@Composable
fun LocationItem(selected: Boolean, id: Int, name: String, onClick: (Int) -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Box(
            modifier = Modifier
                .clickable { onClick(id) }
                .padding(horizontal = 6.dp, vertical = 6.dp)
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
                fontWeight = when (selected) {
                    true -> FontWeight.Bold
                    false -> FontWeight.Normal
                }
            )
        }
    }
}

@Preview
@Composable
fun CharacterCarouselPreview() {

}