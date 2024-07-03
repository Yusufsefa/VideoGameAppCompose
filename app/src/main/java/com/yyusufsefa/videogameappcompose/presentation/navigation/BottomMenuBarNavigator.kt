package com.yyusufsefa.videogameappcompose.presentation.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yyusufsefa.videogameappcompose.R
import com.yyusufsefa.videogameappcompose.presentation.detail.DetailVideoGameScreen
import com.yyusufsefa.videogameappcompose.presentation.favorite.FavoriteScreen
import com.yyusufsefa.videogameappcompose.presentation.home.HomeScreen
import com.yyusufsefa.videogameappcompose.presentation.navigation.components.BottomNavItem
import com.yyusufsefa.videogameappcompose.presentation.navigation.components.VideoGameBottomNavigationBar
import com.yyusufsefa.videogameappcompose.presentation.search.SearchScreen

@Composable
fun BottomMenuBarNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavItem(
                icon = R.drawable.ic_home,
                title = "Home"
            ),
            BottomNavItem(
                icon = R.drawable.ic_search_bottom_menu_bar,
                title = "Search"
            ),
            BottomNavItem(
                icon = R.drawable.ic_favorite_border,
                title = "Favorite"
            )
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.FavoriteScreen.route -> 2
        else -> 0
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.FavoriteScreen.route
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bg_home_screen)), bottomBar = {
            if (isBottomBarVisible) {
                VideoGameBottomNavigationBar(
                    modifier = Modifier.background(colorResource(id = R.color.bg_home_screen)),
                    items = bottomNavigationItems,
                    selectedItem = selectedItem
                ) { index ->
                    when (index) {
                        0 -> navigateToTab(navController, Route.HomeScreen.route)
                        1 -> navigateToTab(navController, Route.SearchScreen.route)
                        2 -> navigateToTab(navController, Route.FavoriteScreen.route)
                    }
                }
            }
        }
    ) { paddingValues ->
        val bottomPadding = paddingValues.calculateBottomPadding()

        NavHost(
            modifier = Modifier
                .padding(bottom = bottomPadding),
            navController = navController,
            startDestination = Route.HomeScreen.route
        ) {

            composable(route = Route.HomeScreen.route) {
                HomeScreen(navigateToDetail = {
                    navigateToDetails(navController, it)
                }, navigateToSearch = {
                    navigateToTab(navController, Route.SearchScreen.route)
                })
            }

            composable(route = Route.SearchScreen.route) {
                OnBackClickStateSaver(navController)
                SearchScreen(navController = navController, navigateToDetail = {
                    navigateToDetails(navController, it)
                })
            }

            composable(route = Route.DetailVideoGameScreen.route) {
                val videoGameId =
                    navController.previousBackStackEntry?.savedStateHandle?.get<Int>("videoGameId")
                DetailVideoGameScreen(
                    navController = navController,
                    videoGameId = videoGameId
                )
            }

            composable(route = Route.FavoriteScreen.route) {
                FavoriteScreen()
            }

        }
    }

}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screenRoute ->
            popUpTo(screenRoute) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, videoGameId: Int) {
    navController.currentBackStackEntry?.savedStateHandle?.set("videoGameId", videoGameId)
    navController.navigate(
        route = Route.DetailVideoGameScreen.route
    )
}