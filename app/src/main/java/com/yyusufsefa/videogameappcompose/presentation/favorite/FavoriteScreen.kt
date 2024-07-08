package com.yyusufsefa.videogameappcompose.presentation.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yyusufsefa.videogameappcompose.presentation.components.LoadingScreen
import com.yyusufsefa.videogameappcompose.presentation.favorite.components.FavoriteItemCard
import com.yyusufsefa.videogameappcompose.ui.theme.ColorBackground
import com.yyusufsefa.videogameappcompose.ui.theme.Dimens

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
) {

    val favoriteState by viewModel.state.collectAsState()
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(key1 = true) {
        viewModel.getFavoriteVideoGames()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
    ) {

        Text(
            modifier = Modifier.padding(Dimens.MarginM),
            text = "Favorite",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.White
        )

        when {
            favoriteState.isLoading -> LoadingScreen()

            favoriteState.videoGame.isNotEmpty() -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    state = lazyGridState,
                    contentPadding = PaddingValues(Dimens.MarginXXS)
                ) {
                    items(favoriteState.videoGame) { videoGame ->
                        FavoriteItemCard(
                            modifier = Modifier.padding(Dimens.MarginXXS),
                            videoGame = videoGame,
                            onClick = {
                                videoGame.id?.let { id -> navigateToDetail(id) }
                            }
                        )
                    }
                }
            }

            favoriteState.error.isNotEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = Dimens.MarginM),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = favoriteState.error,
                        color = Color.White
                    )
                }
            }

        }

    }
}

