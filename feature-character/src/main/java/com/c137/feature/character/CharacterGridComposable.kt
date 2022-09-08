package com.c137.feature.character

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.c137.common.Catchphrase
import com.c137.domain.model.PresentationCharacter
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun CharacterScaffold(viewModel: CharacterGridViewModel = viewModel()) {
    Scaffold(topBar = {
        TopBar(
            expandable = viewModel.isExpandable,
            searchKeyword = viewModel.searchKeyword,
            onClick = { viewModel.toggleIsExpandable() },
            onValueChange = { viewModel.updateSearchKeyword(it) }
        )
    }) {
        Grid(
            pagingCharacters = viewModel.pagingCharacters,
            expandable = viewModel.isExpandable,
            paddingValues = it
        ) {
            viewModel.expand()
        }
    }
}

@Composable
private fun TopBar(
    expandable: Boolean,
    searchKeyword: String,
    onClick: () -> Unit,
    onValueChange: (String) -> Unit
) {
    val label = remember(Unit) { Catchphrase.burp() }
    val placeholder = remember(Unit) { Catchphrase.burp() }
    TopAppBar(title = {
        TextField(
            value = searchKeyword,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder) },
            singleLine = true
        )
    }, navigationIcon = {
        IconButton(
            onClick = onClick,
            enabled = !expandable
        ) {
            if (expandable) {
                Image(
                    painter = painterResource(id = R.drawable.ic_character),
                    contentDescription = ""
                )
            } else {
                Image(
                    imageVector = Icons.Filled.Close,
                    contentDescription = ""
                )
            }
        }
    })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Grid(
    pagingCharacters: Flow<PagingData<PresentationCharacter>>,
    expandable: Boolean,
    paddingValues: PaddingValues,
    onClick: (character: PresentationCharacter) -> Unit
) {
    val lazyPagingCharacters = pagingCharacters.collectAsLazyPagingItems()
    val cellCount = when (expandable) {
        true -> 3
        false -> 2
    }
    val lazyGridState = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(cellCount),
        modifier = Modifier.padding(paddingValues),
        state = lazyGridState
    ) {
        items(lazyPagingCharacters) {
//        for (i in 0 until 10) {
//            item {
            GridCell(
                expandable = expandable,
                character = it!!,
                onClick = onClick
            )
//        }
        }
    }
}

@Composable
private fun GridCell(
    expandable: Boolean,
    character: PresentationCharacter,
    onClick: (character: PresentationCharacter) -> Unit
) {
    Surface(
        modifier = Modifier.clickable { onClick.invoke(character) }
    ) {
        GlideImage(
            imageModel = character.image,
            modifier = Modifier.aspectRatio(1f),
            contentScale = ContentScale.Crop,
            placeHolder = painterResource(id = R.drawable.ic_place_holder),
            previewPlaceholder = R.drawable.ic_place_holder
        )
        if (character.isDead) {
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .alpha(0.5f)
                    .background(MaterialTheme.colors.error)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_dead),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(
                            when (expandable) {
                                true -> 48.dp
                                false -> 72.dp
                            }
                        ),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }
    }
}

@Preview
@Composable
private fun DefaultTopAppBarPreview() {
    TopBar(expandable = true, searchKeyword = "", {}, {})
}

@Preview
@Composable
private fun DefaultTopAppBarWithSearchKeywordPreview() {
    TopBar(expandable = true,
        searchKeyword = Catchphrase.burp(), {}, {})
}

@Preview
@Composable
private fun ClosableTopAppBarPreview() {
    TopBar(expandable = false, searchKeyword = "", {}, {})
}

@Preview
@Composable
private fun DefaultGridPreview() {
    Grid(
        pagingCharacters = flowOf(PagingData.from(listOf(toxicRick))),
        expandable = true,
        paddingValues = PaddingValues(0.dp)
    ) {}
}

@Preview
@Composable
private fun ExpandedGridPreview() {
    Grid(
        pagingCharacters = flowOf(PagingData.from(listOf(toxicRick))),
        expandable = false,
        paddingValues = PaddingValues(0.dp)
    ) {}
}

private val toxicRick = PresentationCharacter(
    id = 361,
    name = "Toxic Rick",
    image = "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
    isDead = true
)