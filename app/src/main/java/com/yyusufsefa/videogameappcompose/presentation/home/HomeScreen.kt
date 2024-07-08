package com.yyusufsefa.videogameappcompose.presentation.home

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yyusufsefa.videogameappcompose.domain.model.VideoGame
import com.yyusufsefa.videogameappcompose.presentation.components.LoadingScreen
import com.yyusufsefa.videogameappcompose.presentation.components.PageIndicator
import com.yyusufsefa.videogameappcompose.presentation.components.VideoGameCard
import com.yyusufsefa.videogameappcompose.presentation.components.VideoGameSearchBar
import com.yyusufsefa.videogameappcompose.presentation.home.components.VideoGameHeaderCard
import com.yyusufsefa.videogameappcompose.ui.theme.ColorBackground
import com.yyusufsefa.videogameappcompose.ui.theme.ColorRating
import com.yyusufsefa.videogameappcompose.ui.theme.Dimens
import com.yyusufsefa.videogameappcompose.ui.theme.VideoGameAppComposeTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
    navigateToSearch: () -> Unit
) {
    val homeState by viewModel.homeState.collectAsState()
    val context = LocalContext.current

    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        if (homeState.videoGames.isEmpty() && !homeState.isLoading) {
            viewModel.onEvent(HomeEvent.GetVideoGames(1, 20))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
    ) {

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = Dimens.MarginM),
            text = "Video Games",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        when {
            homeState.isLoading -> LoadingScreen()
            homeState.error.isNotEmpty() -> {
                Toast.makeText(context, homeState.error, Toast.LENGTH_LONG).show()
            }

            homeState.videoGames.isNotEmpty() -> ContentScreen(
                homeState,
                lazyGridState,
                navigateToDetail,
                navigateToSearch
            )
        }
    }
}

@Composable
fun ContentScreen(
    homeState: HomeState,
    lazyGridState: LazyGridState,
    navigateToDetail: (Int) -> Unit,
    navigateToSearch: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(Dimens.MarginS))

        TopHeaderSection(headerVideoGames = homeState.headerVideoGames, navigateToDetail)

        Spacer(modifier = Modifier.height(Dimens.MarginXS))

        Row(
            modifier = Modifier
                .padding(horizontal = Dimens.MarginM)
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

            VideoGameSearchBar(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                hint = "Search",
                isEnabled = false,
                cornerShape = RoundedCornerShape(Dimens.MarginL),
                onSearchClicked = {
                    navigateToSearch()
                }
            )
        }

        Spacer(modifier = Modifier.height(Dimens.MarginS))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            state = lazyGridState,
            contentPadding = PaddingValues(Dimens.MarginXXS)
        ) {
            items(homeState.videoGames) { videoGame ->
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
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopHeaderSection(headerVideoGames: List<VideoGame>, navigateToDetail: (Int) -> Unit) {

    val pagerState = rememberPagerState(pageCount = headerVideoGames::size)

    Box(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = Dimens.MarginM)
        ) {
            item {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    VideoGameHeaderCard(
                        videoGame = headerVideoGames[it],
                        onClick = navigateToDetail
                    )
                }
            }
        }

        PageIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(Dimens.MarginXS),
            pagesSize = headerVideoGames.size,
            selectedPage = pagerState.currentPage,
            selectedColor = ColorRating
        )
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    VideoGameAppComposeTheme {
        HomeScreen(navigateToDetail = {}, navigateToSearch = {})
    }
}