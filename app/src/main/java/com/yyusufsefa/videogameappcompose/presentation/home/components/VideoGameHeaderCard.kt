package com.yyusufsefa.videogameappcompose.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.yyusufsefa.videogameappcompose.domain.model.VideoGame
import com.yyusufsefa.videogameappcompose.ui.theme.Dimens
import com.yyusufsefa.videogameappcompose.ui.theme.VideoGameAppComposeTheme

@Composable
fun VideoGameHeaderCard(
    modifier: Modifier = Modifier,
    videoGame: VideoGame,
    onClick: ((Int) -> Unit)? = null
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Dimens.MarginXXXL))
            .height(Dimens.VideoGameHeaderCardHeight)
            .clickable { onClick?.invoke(videoGame.id ?: 0) }
    ) {
        val context = LocalContext.current

        Box {
            AsyncImage(
                model = ImageRequest.Builder(context).data(videoGame.imageUrl).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = Dimens.MarginM, bottom = Dimens.MarginXL),
                text = videoGame.name ?: "",
                color = Color.White,
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.SemiBold),
                fontSize = 24.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(Dimens.MarginM)
                    .background(Color(0x80000000), shape = RoundedCornerShape(Dimens.MarginXS))
                    .padding(horizontal = Dimens.MarginXS, vertical = Dimens.MarginXXS)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star Icon",
                    tint = Color.Yellow,
                    modifier = Modifier.size(Dimens.MarginL)
                )
                Spacer(modifier = Modifier.width(Dimens.MarginXXS))
                Text(
                    text = videoGame.rating.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VideoGameHeaderCardPreview() {
    VideoGameAppComposeTheme {
        VideoGameHeaderCard(
            videoGame = VideoGame(
                id = 1,
                name = "God of War",
                imageUrl = "",
                rating = 5.0
            )
        )
    }
}