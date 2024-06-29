package com.yyusufsefa.videogameappcompose.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yyusufsefa.videogameappcompose.R
import com.yyusufsefa.videogameappcompose.presentation.components.VideoGameSearchBar
import com.yyusufsefa.videogameappcompose.presentation.home.components.VideoGameCard


@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
    navigateToDetail: (Int) -> Unit
) {
    val searchViewState by viewModel.searchState.collectAsState()
    var searchQuery by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(searchQuery) {
        viewModel.onEvent(SearchEvent.GetSearchVideoGames(searchQuery))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bg_home_screen))
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(horizontal = 16.dp)
        ) {
            Card(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        navController.popBackStack()
                    }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.bg_arrow_back).copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Icon",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            VideoGameSearchBar(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                hint = "Search",
                onTextChange = {
                    searchQuery = it
                    viewModel.onEvent(SearchEvent.GetSearchVideoGames(it))
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (searchViewState.searchVideoGames.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(searchViewState.searchVideoGames) { videoGame ->
                    VideoGameCard(
                        videoGame = videoGame,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .padding(8.dp),
                        onClick = {
                            videoGame.id?.let { id -> navigateToDetail(id) }
                        }
                    )
                }
            }
        } else if (searchViewState.error.isNotEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = searchViewState.error)
            }
        }
    }
}

