package com.yyusufsefa.videogameappcompose.presentation.navigation

sealed class Route(
    val route: String
) {
    data object OnBoardingNavigation : Route(route = "onBoardingNavigation")
    data object BottomNavigation : Route(route = "homeNavigation")
    data object OnBoardingScreen : Route(route = "onBoarding")
    data object HomeScreen : Route(route = "home")
    data object SearchScreen : Route(route = "search")
    data object DetailVideoGameScreen : Route(route = "detail")
    data object FavoriteScreen : Route(route = "favorite")
    data object BottomMenuBar : Route(route = "bottomMenuBar")

}