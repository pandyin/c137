package com.c137.feature.character

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
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.c137.domain.model.PresentationCharacter
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CharacterScaffold(viewModel: CharacterListViewModel = viewModel()) {
    Scaffold(topBar = { TopBar(expandable = viewModel.expandable) }) {
        Grid(expandable = viewModel.expandable, paddingValues = it)
    }
}

@Composable
private fun TopBar(expandable: MutableState<Boolean>) {
    TopAppBar(title = {}, navigationIcon = {
        IconButton(
            onClick = { expandable.value = true },
            enabled = !expandable.value
        ) {
            if (expandable.value) {
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

@Composable
private fun Grid(expandable: MutableState<Boolean>, paddingValues: PaddingValues) {
    val cellCount = when (expandable.value) {
        true -> 3
        false -> 1
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(cellCount),
        modifier = Modifier.padding(paddingValues)
    ) {
        for (i in 1..10) {
            item {
                GridCell(PresentationCharacter()) {
                    expandable.value = false
                }
            }
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
            imageModel = "https://cdn.domestika.org/c_limit,dpr_1.0,f_auto,q_auto,w_820/v1546529902/content-items/002/696/999/rick-original.jpg?1546529902",
            modifier = Modifier
                .size(128.dp)
                .clickable { onClick.invoke(character) },
            contentScale = ContentScale.Fit,
            placeHolder = painterResource(id = R.drawable.ic_place_holder),
            previewPlaceholder = R.drawable.ic_place_holder
        )
    }
}

@Preview
@Composable
private fun DefaultTopAppBarPreview() {
    val expandable = rememberUpdatedState {
        mutableStateOf(true)
    }
    TopBar(expandable = expandable.value())
}

@Preview
@Composable
private fun ClosableTopAppBarPreview() {
    val expandable = rememberUpdatedState {
        mutableStateOf(false)
    }
    TopBar(expandable = expandable.value())
}

@Preview
@Composable
private fun DefaultGridPreview(viewModel: CharacterListViewModel = viewModel()) {
    val expandable = rememberUpdatedState {
        mutableStateOf(true)
    }
    Grid(expandable = expandable.value(), paddingValues = PaddingValues(0.dp))
}

@Preview
@Composable
private fun SingleCellPerRowGridPreview(viewModel: CharacterListViewModel = viewModel()) {
    val expandable = rememberUpdatedState {
        mutableStateOf(false)
    }
    Grid(expandable = expandable.value(), paddingValues = PaddingValues(0.dp))
}
