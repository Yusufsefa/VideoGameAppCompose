package com.yyusufsefa.videogameappcompose.presentation.navigation

sealed class Route(
    val route: String
) {
    data object OnBoardingScreen : Route(route = "onBoarding")
    data object HomeScreen : Route(route = "home")

    data object OnBoardingNavigation : Route(route = "onBoardingNavigation")

    data object HomeNavigation : Route(route = "homeNavigation")
}