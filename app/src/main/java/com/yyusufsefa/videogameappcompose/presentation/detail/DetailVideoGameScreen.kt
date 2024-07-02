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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.colorResource
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
            .background(colorResource(id = R.color.bg_detail_screen))
    ) {
        when {
            detailState.isLoading -> LoadingScreen()
            detailState.error.isNotEmpty() -> {
                LaunchedEffect(detailState.error) {
                    Toast.makeText(context, detailState.error, Toast.LENGTH_LONG).show()
                }
            }

            detailState.videoGameDetail != null -> DetailContent(
                detailState.videoGameDetail!!,
                navToBack = { navController.popBackStack() }
            )
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
fun DetailContent(
    videoGameDetail: VideoGameDetail,
    navToBack: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bg_detail_screen))
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)),
                model = ImageRequest.Builder(context).data(videoGameDetail.imageUrl).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Card(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .clickable { navToBack() },
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.bg_arrow_back).copy(alpha = 0.5f),
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
                        .size(40.dp)
                        .padding(6.dp)
                )
            }

            Card(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.bg_arrow_back).copy(alpha = 0.5f),
                    contentColor = Color.Blue,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.DarkGray
                )
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(6.dp)
                )
            }

            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                text = videoGameDetail.name,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = videoGameDetail.releaseDate,
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

            PlatformIcons(platforms = videoGameDetail.platforms)

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
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = videoGameDetail.rating.toString(),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier.padding(16.dp),
            text = "About Game",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = Html.fromHtml(videoGameDetail.desc, Html.FROM_HTML_MODE_COMPACT).toString(),
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))
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
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

                PlatformStatus.PlayStation -> Image(
                    painter = painterResource(id = R.drawable.ic_ps_platform),
                    contentDescription = "PlayStation Platform",
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

                PlatformStatus.Xbox -> Image(
                    painter = painterResource(id = R.drawable.ic_xbox_platform),
                    contentDescription = "Xbox Platform",
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

                null -> {
                    // No matching platform, do nothing
                }
            }
        }
    }
}
