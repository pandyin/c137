package com.c137.feature.character

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.darkColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Grid3x3
import androidx.compose.material.icons.filled.Grid4x4
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.c137.common.Catchphrase
import com.c137.common.model.toLocation
import com.c137.domain.model.PresentationCharacter
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

@Composable
fun CharacterScaffold(viewModel: CharacterGridViewModel = viewModel()) {
    val searchKeyword by viewModel.searchInput.collectAsState()
    MaterialTheme(colors = darkColors()) {
        Scaffold(topBar = {
            TopBar(
                isExpandable = viewModel.isExpandable,
                searchKeyword = searchKeyword,
                onClick = { viewModel.toggleIsExpandable() },
                onValueChange = { viewModel.updateSearchInput(it) }
            )
        }) {
            Grid(
                paging = viewModel.pagingCharacters,
                scrollingState = viewModel.scrollingState,
                isExpandable = viewModel.isExpandable,
                paddingValues = it
            ) { index, character ->
                viewModel.expand(index, character)
            }
        }
    }
}

@Composable
private fun TopBar(
    isExpandable: Boolean,
    searchKeyword: String,
    onClick: () -> Unit,
    onValueChange: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    TopAppBar(
        title = {
            TextField(
                value = searchKeyword,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                label = {
                    Text(
                        text = "Names, Species, Whereabouts and Dimension",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                },
                placeholder = {
                    Text(
                        text = "Rick, Alien, Microverse, C-137, etc.",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Image(
                    painter = painterResource(id = R.drawable.ic_character),
                    contentDescription = ""
                )
            }
        },
        actions = {
            if (isExpandable) {
                IconButton(onClick = onClick) {
                    Image(
                        imageVector = Icons.Filled.Grid3x3,
                        contentDescription = ""
                    )
                }
            } else {
                IconButton(onClick = onClick) {
                    Image(
                        imageVector = Icons.Filled.Grid4x4,
                        contentDescription = ""
                    )
                }
            }
        }
    )
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Grid(
    paging: Flow<PagingData<PresentationCharacter>>,
    scrollingState: StateFlow<ScrollingState>,
    isExpandable: Boolean,
    paddingValues: PaddingValues,
    onClick: (index: Int, character: PresentationCharacter) -> Unit
) {
    val lazyGridState = rememberLazyGridState()
    val lazyPaging = paging.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        scrollingState
            .filter { it is ScrollingState.ScrollTo }
            .map { it as ScrollingState.ScrollTo }
            .collect { lazyGridState.scrollToItem(it.index) }
    }

    val cellCount = when (isExpandable) {
        true -> 4
        false -> 2
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(cellCount),
        modifier = Modifier.padding(paddingValues),
        state = lazyGridState
    ) {
        itemsIndexed(lazyPaging) { index, character ->
//        for (i in 0 until 10) {
//            item {
            GridCell(
                indexedCharacter = index to character!!,
//                indexedCharacter = i to toxicRick,
                isExpandable = isExpandable,
                onClick = onClick
            )
//            }
        }
    }
}

@Composable
private fun GridCell(
    indexedCharacter: Pair<Int, PresentationCharacter>,
    isExpandable: Boolean,
    onClick: (index: Int, character: PresentationCharacter) -> Unit
) {
    val (index, character) = indexedCharacter
    Surface(
        modifier = Modifier.clickable { onClick(index, character) }
    ) {
        Box {
            Image(url = character.image)
            if (!isExpandable) {
                SpeciesAndWhereaboutsColumn(character = character)
            }
            Name(
                name = character.name, modifier = Modifier
                    .padding(6.dp)
                    .align(Alignment.BottomCenter)
            )
        }
        if (character.isDead) {
            DeadBox(isExpandable)
        }
    }
}

@Composable
private fun Image(url: String) {
    GlideImage(
        imageModel = url,
        modifier = Modifier.aspectRatio(1f),
        contentScale = ContentScale.Crop,
        placeHolder = painterResource(id = R.drawable.ic_place_holder),
        previewPlaceholder = R.drawable.ic_place_holder
    )
}

@Composable
private fun SpeciesAndWhereaboutsColumn(character: PresentationCharacter) {
    Column(modifier = Modifier.padding(6.dp)) {
        Text(
            text = character.species,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        listOf(character.origin, character.location)
            .filter { !it.isUnknown() }
            .map { it.name }
            .distinct()
            .forEach {
                Text(
                    text = it,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        character.dimensions.forEach {
            Text(
                text = it,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun Name(name: String, modifier: Modifier) {
    Text(
        text = name,
        modifier = modifier,
        color = Color.White,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2
    )
}

@Composable
private fun DeadBox(isExpandable: Boolean) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .alpha(0.4f)
            .background(MaterialTheme.colors.error)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_dead),
            contentDescription = "",
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.TopEnd)
                .size(
                    when (isExpandable) {
                        true -> 24.dp
                        false -> 48.dp
                    }
                ),
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}

@Preview
@Composable
private fun DefaultTopAppBarPreview() {
    TopBar(isExpandable = true, searchKeyword = "", {}, {})
}

@Preview
@Composable
private fun DefaultTopAppBarWithSearchKeywordPreview() {
    TopBar(isExpandable = true,
        searchKeyword = Catchphrase.burp(), {}, {})
}

@Preview
@Composable
private fun ClosableTopAppBarPreview() {
    TopBar(isExpandable = false, searchKeyword = "", {}, {})
}

@Preview
@Composable
private fun DefaultGridPreview() {
    Grid(
        paging = flowOf(PagingData.from(listOf(toxicRick))),
        scrollingState = MutableStateFlow(ScrollingState.ScrollTo(index = 0)),
        isExpandable = true,
        paddingValues = PaddingValues(0.dp)
    ) { _, _ -> }
}

@Preview
@Composable
private fun ExpandedGridPreview() {
    Grid(
        paging = flowOf(PagingData.from(listOf(toxicRick))),
        scrollingState = MutableStateFlow(ScrollingState.ScrollTo(index = 0)),
        isExpandable = false,
        paddingValues = PaddingValues(0.dp)
    ) { _, _ -> }
}

private val toxicRick = PresentationCharacter(
    id = 361,
    name = "Toxic Rick Toxic Rick",
    image = "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
    species = "Human",
    origin = "Earth".toLocation(),
    location = "Earth".toLocation(),
    dimensions = emptyList(),
    isDead = false
)