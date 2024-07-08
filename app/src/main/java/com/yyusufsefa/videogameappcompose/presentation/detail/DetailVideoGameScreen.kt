package com.yyusufsefa.videogameappcompose.presentation.detail

import android.text.Html
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.yyusufsefa.videogameappcompose.R
import com.yyusufsefa.videogameappcompose.domain.model.PlatformStatus
import com.yyusufsefa.videogameappcompose.domain.model.VideoGameDetail
import com.yyusufsefa.videogameappcompose.presentation.components.LoadingScreen
import com.yyusufsefa.videogameappcompose.ui.theme.ColorArrowBack
import com.yyusufsefa.videogameappcompose.ui.theme.ColorDetailScreen
import com.yyusufsefa.videogameappcompose.ui.theme.Dimens


@Composable
fun DetailVideoGameScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    navController: NavController,
    videoGameId: Int?
) {
    val detailState by viewModel.detailState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(videoGameId) {
        videoGameId?.let {
            viewModel.onEvent(DetailEvent.GetDetailVideoGames(it))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorDetailScreen)
    ) {
        when {
            detailState.isLoading -> LoadingScreen()
            detailState.error.isNotEmpty() -> {
                LaunchedEffect(detailState.error) {
                    Toast.makeText(context, detailState.error, Toast.LENGTH_LONG).show()
                }
            }

            detailState.videoGameDetail != null -> DetailContent(
                detailState = detailState,
                navToBack = { navController.popBackStack() },
                onFavoriteClick = { videoGameDetail, isFavorite ->
                    viewModel.onEvent(DetailEvent.OnFavoriteVideoGame(videoGameDetail, isFavorite))
                }
            )
        }
    }
}

@Composable
fun DetailContent(
    detailState: DetailViewState,
    navToBack: () -> Unit,
    onFavoriteClick: (VideoGameDetail, Boolean) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorDetailScreen)
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.DetailImageHeight)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = Dimens.MarginM,
                            bottomStart = Dimens.MarginM
                        )
                    ),
                model = ImageRequest.Builder(context)
                    .data(detailState.videoGameDetail?.imageUrl).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Card(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(Dimens.MarginM)
                    .clickable { navToBack() },
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
                        .size(Dimens.Margin4XL)
                        .padding(Dimens.IconPadding)
                )
            }

            Card(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(Dimens.MarginM)
                    .clickable {
                        detailState.videoGameDetail?.let {
                            onFavoriteClick(it, !detailState.isFavorite)
                        }
                    },
                colors = CardDefaults.cardColors(
                    containerColor = ColorArrowBack.copy(alpha = 0.5f),
                    contentColor = Color.Blue,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.DarkGray
                )
            ) {
                Icon(
                    imageVector = if (detailState.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite Icon",
                    tint = if (detailState.isFavorite) Color.Red else Color.White,
                    modifier = Modifier
                        .size(Dimens.Margin4XL)
                        .padding(Dimens.IconPadding)
                )
            }

            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(vertical = Dimens.MarginM),
                text = detailState.videoGameDetail?.name ?: "",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        }

        Spacer(modifier = Modifier.height(Dimens.MarginXS))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.MarginXXL),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = detailState.videoGameDetail?.releaseDate ?: "",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
            )

            PlatformIcons(platforms = detailState.videoGameDetail?.platforms ?: emptyList())

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating Icon",
                    tint = Color.Yellow,
                    modifier = Modifier.size(Dimens.MarginXL)
                )

                Spacer(modifier = Modifier.width(Dimens.MarginXXS))

                Text(
                    text = detailState.videoGameDetail?.rating.toString(),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(Dimens.MarginM))

        Text(
            modifier = Modifier.padding(Dimens.MarginM),
            text = "About Game",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Text(
            modifier = Modifier.padding(horizontal = Dimens.MarginM),
            text = Html.fromHtml(detailState.videoGameDetail?.desc, Html.FROM_HTML_MODE_COMPACT)
                .toString(),
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(Dimens.MarginM))
    }
}


@Composable
fun PlatformIcons(platforms: List<String>) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        platforms.forEach { platformString ->
            val platform = PlatformStatus.fromString(platformString)
            when (platform) {
                PlatformStatus.PC -> Image(
                    painter = painterResource(id = R.drawable.ic_pc_platform),
                    contentDescription = "PC Platform",
                    modifier = Modifier.padding(horizontal = Dimens.MarginXXS)
                )

                PlatformStatus.PlayStation -> Image(
                    painter = painterResource(id = R.drawable.ic_ps_platform),
                    contentDescription = "PlayStation Platform",
                    modifier = Modifier.padding(horizontal = Dimens.MarginXXS)
                )

                PlatformStatus.Xbox -> Image(
                    painter = painterResource(id = R.drawable.ic_xbox_platform),
                    contentDescription = "Xbox Platform",
                    modifier = Modifier.padding(horizontal = Dimens.MarginXXS)
                )

                null -> {
                    // No matching platform, do nothing
                }
            }
        }
    }
}
