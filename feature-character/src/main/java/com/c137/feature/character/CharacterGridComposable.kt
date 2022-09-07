package com.c137.feature.character

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import kotlinx.coroutines.flow.emptyFlow

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
            viewModel.toggleIsExpandable()
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
        false -> 1
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(cellCount),
        modifier = Modifier.padding(paddingValues)
    ) {
        items(lazyPagingCharacters) {
            GridCell(
                character = it!!,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun GridCell(
    character: PresentationCharacter,
    onClick: (character: PresentationCharacter) -> Unit
) {
    Surface {
        GlideImage(
            imageModel = character.image,
            modifier = Modifier
                .size(128.dp)
                .clickable { onClick.invoke(character) },
            contentScale = ContentScale.Crop,
            placeHolder = painterResource(id = R.drawable.ic_place_holder),
            previewPlaceholder = R.drawable.ic_place_holder
        )
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
    TopBar(expandable = true, searchKeyword = Catchphrase.burp(), {}, {})
}

@Preview
@Composable
private fun ClosableTopAppBarPreview() {
    TopBar(expandable = false, searchKeyword = "", {}, {})
}

@Preview
@Composable
private fun DefaultGridPreview(viewModel: CharacterGridViewModel = viewModel()) {
    Grid(pagingCharacters = emptyFlow(), expandable = true, paddingValues = PaddingValues(0.dp)) {}
}

@Preview
@Composable
private fun SingleCellPerRowGridPreview(viewModel: CharacterGridViewModel = viewModel()) {
    Grid(pagingCharacters = emptyFlow(), expandable = false, paddingValues = PaddingValues(0.dp)) {}
}