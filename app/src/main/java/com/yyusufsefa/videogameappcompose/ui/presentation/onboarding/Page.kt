package com.yyusufsefa.videogameappcompose.ui.presentation.onboarding

import androidx.annotation.DrawableRes
import com.yyusufsefa.videogameappcompose.R

data class Page(
    @DrawableRes val image: Int,
    val title: String,
    val desc: String
)

val pages = listOf(
    Page(
        R.drawable.onboarding_first,
        "Enter the World of Gaming!",
        "Discover the latest news, reviews, trailers, and more for your favorite games. All in one place!"
    ),
    Page(
        R.drawable.onboarding_second,
        "A Community for Gaming Enthusiasts!",
        "Connect with other gamers, share ideas, and showcase your games. Together, let's make the gaming world even more enjoyable!"
    ),
    Page(
        R.drawable.onboarding_third,
        "Don't Miss Out: Stay Updated with the Latest Gaming News!",
        "Be the first to know about the latest game releases, tournaments, discounts, and more. Stay tuned to the gaming world!"
    )
)