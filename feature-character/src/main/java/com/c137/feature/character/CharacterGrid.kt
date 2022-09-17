package com.c137.feature.character

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Grid3x3
import androidx.compose.material.icons.outlined.Grid4x4
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.c137.common.Catchphrase
import com.c137.common.theme.darkColors
import com.c137.common.theme.lightColors
import com.c137.domain.model.PresentationCharacter
import com.c137.domain.model.PresentationLocation
import com.c137.feature.location.LocationCarousel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

@Composable
fun CharacterGrid(viewModel: CharacterGridViewModel = hiltViewModel()) {
    val searchKeyword by viewModel.searchInput.collectAsState()
    val locations by viewModel.locations.collectAsState()
    MaterialTheme(colors = if (isSystemInDarkTheme()) darkColors else lightColors) {
        Scaffold(topBar = {
            TopBar(
                isExpanded = viewModel.isExpanded,
                searchKeyword = searchKeyword,
                onClick = { viewModel.toggleIsExpanded() },
                onValueChange = { viewModel.updateSearchInput(it) }
            )
        }) {
            Column {
                LocationCarousel(
                    locations = locations,
                    onClick = { viewModel.toggleLocation(it) })
                Grid(
                    paging = viewModel.pagingCharacters,
                    scrollingState = viewModel.scrollingState,
                    isExpanded = viewModel.isExpanded,
                    paddingValues = it
                ) { index, character ->
                    viewModel.expand(index, character)
                }
            }
        }
    }
}

@Composable
private fun TopBar(
    isExpanded: Boolean,
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
                        color = MaterialTheme.colors.onSurface,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                },
                placeholder = {
                    Text(
                        text = "Rick, Alien, Microverse, C-137, etc.",
                        color = MaterialTheme.colors.onBackground,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.onSurface,
                    cursorColor = MaterialTheme.colors.onSurface,
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
            if (isExpanded) {
                IconButton(onClick = onClick) {
                    Image(
                        imageVector = Icons.Outlined.Grid4x4,
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
                    )
                }
            } else {
                IconButton(onClick = onClick) {
                    Image(
                        imageVector = Icons.Outlined.Grid3x3,
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
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
    isExpanded: Boolean,
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

    val cellCount = when (isExpanded) {
        true -> 2
        false -> 4
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
                isExpanded = isExpanded,
                onClick = onClick
            )
//            }
        }
    }
}

@Composable
private fun GridCell(
    indexedCharacter: Pair<Int, PresentationCharacter>,
    isExpanded: Boolean,
    onClick: (index: Int, character: PresentationCharacter) -> Unit
) {
    val (index, character) = indexedCharacter
    Surface(
        modifier = Modifier.clickable { onClick(index, character) }
    ) {
        Box {
            Image(url = character.image)
            if (isExpanded) {
                SpeciesAndWhereaboutsColumn(character = character)
            }
            Name(
                isExpanded = isExpanded,
                name = character.name,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
        if (character.isDead) {
            DeadBox(isExpanded)
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
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        listOf(character.origin, character.lastKnown)
            .map { it.name }
            .distinct()
            .forEach {
                Text(
                    text = it,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
    }
}

@Composable
private fun Name(isExpanded: Boolean, name: String, modifier: Modifier) {
    Text(
        text = name,
        modifier = modifier.padding(6.dp),
        color = Color.White,
        fontSize = when (isExpanded) {
            true -> 18.sp
            false -> 9.sp
        },
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2
    )
}

@Composable
private fun DeadBox(isExpanded: Boolean) {
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
                .padding(12.dp)
                .align(Alignment.TopEnd)
                .size(
                    when (isExpanded) {
                        true -> 48.dp
                        false -> 24.dp
                    }
                ),
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}

@Preview
@Composable
private fun DefaultTopAppBarPreview() {
    TopBar(isExpanded = true, searchKeyword = "", {}, {})
}

@Preview
@Composable
private fun DefaultTopAppBarWithSearchKeywordPreview() {
    TopBar(isExpanded = true, searchKeyword = Catchphrase.burp(), {}, {})
}

@Preview
@Composable
private fun ClosableTopAppBarPreview() {
    TopBar(isExpanded = false, searchKeyword = "", {}, {})
}

@Preview
@Composable
private fun DefaultGridPreview() {
    Grid(
        paging = flowOf(PagingData.from(listOf(toxicRick))),
        scrollingState = MutableStateFlow(ScrollingState.ScrollTo(index = 0)),
        isExpanded = false,
        paddingValues = PaddingValues(0.dp)
    ) { _, _ -> }
}

@Preview
@Composable
private fun ExpandedGridPreview() {
    Grid(
        paging = flowOf(PagingData.from(listOf(toxicRick))),
        scrollingState = MutableStateFlow(ScrollingState.ScrollTo(index = 0)),
        isExpanded = true,
        paddingValues = PaddingValues(0.dp)
    ) { _, _ -> }
}

private val earth = PresentationLocation(
    id = 1,
    name = "Earth",
    type = "null",
    dimension = "null",
    residents = emptyList()
)

private val toxicRick = PresentationCharacter(
    id = 361,
    name = "Toxic Rick Toxic Rick",
    image = "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
    species = "Human",
    origin = earth,
    lastKnown = earth,
    isDead = false
)