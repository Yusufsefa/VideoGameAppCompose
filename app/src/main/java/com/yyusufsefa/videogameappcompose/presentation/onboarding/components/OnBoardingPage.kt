package com.yyusufsefa.videogameappcompose.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yyusufsefa.videogameappcompose.presentation.onboarding.Page
import com.yyusufsefa.videogameappcompose.presentation.onboarding.pages
import com.yyusufsefa.videogameappcompose.ui.theme.VideoGameAppComposeTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {

    Column(modifier = modifier.padding(16.dp)) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = page.image),
            contentDescription = page.title,
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = modifier.padding(vertical = 16.dp, horizontal = 8.dp),
            text = page.title,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = Color.White
        )

        Text(
            modifier = modifier.padding(horizontal = 8.dp),
            text = page.desc,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color.White
        )

    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPagePreview() {
    VideoGameAppComposeTheme {
        OnBoardingPage(
            page = pages[2]
        )
    }
}