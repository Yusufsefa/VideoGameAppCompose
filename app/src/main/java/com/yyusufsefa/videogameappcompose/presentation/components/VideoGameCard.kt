package com.yyusufsefa.videogameappcompose.presentation.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.yyusufsefa.videogameappcompose.domain.model.VideoGame
import com.yyusufsefa.videogameappcompose.ui.theme.ColorVideoGameCard
import com.yyusufsefa.videogameappcompose.ui.theme.Dimens
import com.yyusufsefa.videogameappcompose.ui.theme.VideoGameAppComposeTheme

@Composable
fun VideoGameCard(
    modifier: Modifier = Modifier,
    videoGame: VideoGame,
    onClick: ((Int) -> Unit)? = null
) {

    val context = LocalContext.current

    Box(
        modifier = modifier
            .width(Dimens.VideoGameCardWidth)
            .height(Dimens.VideoGameCardHeight)
            .clickable { videoGame.id?.let { onClick?.invoke(it) } }
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = Dimens.MarginS, vertical = Dimens.Margin4XL)
                .fillMaxWidth()
                .fillMaxHeight(1f)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(Dimens.Margin4XL),
            colors = CardDefaults.cardColors(
                containerColor = ColorVideoGameCard,
                contentColor = Color.Blue,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.DarkGray
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = videoGame.name ?: "",
                    fontSize = 10.sp,
                    color = Color.White,
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.SemiBold),
                    lineHeight = 13.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(Dimens.MarginXXS)
                        .background(Color(0x80000000), shape = RoundedCornerShape(Dimens.MarginXS))
                        .padding(horizontal = Dimens.MarginXS, vertical = Dimens.MarginXXS),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star Icon",
                        tint = Color.Yellow,
                        modifier = Modifier.size(Dimens.MarginS)
                    )
                    Spacer(modifier = Modifier.width(Dimens.MarginXXS))
                    Text(
                        text = videoGame.rating.toString(),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .align(Alignment.TopCenter),
            shape = RoundedCornerShape(Dimens.MarginXL)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(context).data(videoGame.imageUrl).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun VideGameCardPreview() {
    VideoGameAppComposeTheme {
        VideoGameCard(
            videoGame = VideoGame(
                id = 1,
                name = "Test dflşkgdfşkgdfsdfkdfjkgdhfkgdfdkfjghfdddfgkfd",
                imageUrl = "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/11787/production/_124395517_bbcbreakingnewsgraphic.jpg",
                rating = 5.0,
            )
        )
    }
}



