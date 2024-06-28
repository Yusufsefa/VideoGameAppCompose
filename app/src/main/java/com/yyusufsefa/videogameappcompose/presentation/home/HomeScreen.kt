package com.yyusufsefa.videogameappcompose.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yyusufsefa.videogameappcompose.R
import com.yyusufsefa.videogameappcompose.domain.model.VideoGame
import com.yyusufsefa.videogameappcompose.presentation.components.PageIndicator
import com.yyusufsefa.videogameappcompose.presentation.components.SearchBar
import com.yyusufsefa.videogameappcompose.presentation.home.components.VideoGameCard
import com.yyusufsefa.videogameappcompose.presentation.home.components.VideoGameHeaderCard
import com.yyusufsefa.videogameappcompose.ui.theme.VideoGameAppComposeTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    val homeState = viewModel.homeState.value

    LaunchedEffect(Unit) {
        viewModel.onEvent(HomeEvent.GetVideoGames(1, 20))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bg_home_screen))
    ) {
        when {
            homeState.isLoading -> LoadingScreen()
            homeState.error.isNotEmpty() -> ErrorScreen(homeState.error)
            homeState.videoGames.isNotEmpty() -> ContentScreen(homeState, navigateToDetail)
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(errorMessage: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = errorMessage, color = Color.Red)
    }
}

@Composable
fun ContentScreen(homeState: HomeState, navigateToDetail: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            text = "Video Games",
            color = Color.White,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.SemiBold),
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        TopHeaderSection(headerVideoGames = homeState.headerVideoGames)

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "TOP GAMES",
                color = Color.Cyan,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            SearchBar(
                modifier = Modifier.fillMaxWidth(0.8f),
                hint = "Search",
                cornerShape = RoundedCornerShape(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(homeState.videoGames) { videoGame ->
                VideoGameCard(
                    videoGame = videoGame,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .padding(8.dp),
                    onClick = {
                        navigateToDetail(videoGame.id)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopHeaderSection(headerVideoGames: List<VideoGame>) {

    val pagerState = rememberPagerState(pageCount = headerVideoGames::size)

    Box(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            item {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    VideoGameHeaderCard(videoGame = headerVideoGames[it])
                }
            }
        }

        PageIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            pagesSize = headerVideoGames.size,
            selectedPage = pagerState.currentPage,
            selectedColor = colorResource(id = R.color.bg_rating)
        )
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    VideoGameAppComposeTheme {
        HomeScreen(navigateToDetail = {})
    }
}