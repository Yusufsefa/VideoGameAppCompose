package com.yyusufsefa.videogameappcompose.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yyusufsefa.videogameappcompose.presentation.components.VideoGameCard
import com.yyusufsefa.videogameappcompose.presentation.components.VideoGameSearchBar
import com.yyusufsefa.videogameappcompose.ui.theme.ColorArrowBack
import com.yyusufsefa.videogameappcompose.ui.theme.ColorBackground
import com.yyusufsefa.videogameappcompose.ui.theme.Dimens


@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
    navigateToDetail: (Int) -> Unit
) {
    val searchViewState by viewModel.searchState.collectAsState()
    val searchQuery = searchViewState.query

    LaunchedEffect(searchQuery) {
        viewModel.onEvent(SearchEvent.GetSearchVideoGames(searchQuery))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
    ) {
        Spacer(modifier = Modifier.height(Dimens.MarginM))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.SearchBarSize)
                .padding(horizontal = Dimens.MarginM)
        ) {
            Card(
                modifier = Modifier
                    .size(Dimens.SearchBarSize)
                    .clip(RoundedCornerShape(Dimens.MarginXS))
                    .clickable {
                        navController.popBackStack()
                    },
                colors = CardDefaults.cardColors(
                    containerColor = ColorArrowBack.copy(alpha = 0.5f),
                    contentColor = Color.Blue,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.DarkGray
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(Dimens.SearchBarSize)
                        .padding(Dimens.IconPadding)
                )
            }

            VideoGameSearchBar(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Dimens.MarginS),
                hint = "Search",
                searchQuery = searchQuery,
                onTextChange = {
                    viewModel.onEvent(SearchEvent.UpdateQuery(it))
                },
                onSearchClicked = {
                    viewModel.onEvent(SearchEvent.GetSearchVideoGames(searchQuery))
                }
            )
        }

        Spacer(modifier = Modifier.height(Dimens.MarginM))

        when {
            searchViewState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            searchViewState.searchVideoGames.isNotEmpty() -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(Dimens.MarginXXS)
                ) {
                    items(searchViewState.searchVideoGames) { videoGame ->
                        VideoGameCard(
                            videoGame = videoGame,
                            modifier = Modifier
                                .padding(Dimens.MarginXS),
                            onClick = {
                                videoGame.id?.let { id -> navigateToDetail(id) }
                            }
                        )
                    }
                }
            }

            searchViewState.error.isNotEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = searchViewState.error)
                }
            }
        }
    }
}


