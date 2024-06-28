package com.yyusufsefa.videogameappcompose.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yyusufsefa.videogameappcompose.R
import com.yyusufsefa.videogameappcompose.presentation.components.VideoGameButton
import com.yyusufsefa.videogameappcompose.presentation.onboarding.components.OnBoardingPage
import com.yyusufsefa.videogameappcompose.presentation.components.PageIndicator
import com.yyusufsefa.videogameappcompose.ui.theme.VideoGameAppComposeTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    onBoardingEvent: (OnBoardingEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bg_on_boarding))
    ) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val buttonsState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("Skip", "Next")
                    1 -> listOf("Skip", "Next")
                    2 -> listOf("", "Get Started")
                    else -> listOf("", "")
                }
            }
        }

        val scope = rememberCoroutineScope()

        TextButton(
            modifier = Modifier.align(Alignment.End),
            onClick = {
                if (buttonsState.value[0].isNotEmpty()) {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            page = pagerState.pageCount
                        )
                    }
                }
            }
        ) {
            Text(
                text = buttonsState.value[0],
                fontSize = 20.sp,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.bg_on_boarding_button_color)
            )
        }

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OnBoardingPage(page = pages[it])
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PageIndicator(
                modifier = Modifier
                    .fillMaxWidth(0.24f)
                    .padding(vertical = 16.dp),
                pagesSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            VideoGameButton(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 16.dp),
                text = buttonsState.value[1],
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage == 2) {
                            onBoardingEvent(OnBoardingEvent.SaveOnBoardingState(true))
                        } else {
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1
                            )
                        }
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnBoardingScreenPreview() {
    VideoGameAppComposeTheme {
        OnBoardingScreen(onBoardingEvent = {})
    }
}