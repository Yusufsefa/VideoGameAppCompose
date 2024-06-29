package com.yyusufsefa.videogameappcompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.yyusufsefa.videogameappcompose.presentation.detail.DetailVideoGameScreen
import com.yyusufsefa.videogameappcompose.presentation.home.HomeScreen
import com.yyusufsefa.videogameappcompose.presentation.onboarding.OnBoardingScreen
import com.yyusufsefa.videogameappcompose.presentation.onboarding.OnBoardingViewModel
import com.yyusufsefa.videogameappcompose.presentation.search.SearchScreen


@Composable
fun NavGraph(startDestination: String) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        navigation(
            route = Route.OnBoardingNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onBoardingEvent = viewModel::onEvent)
            }
        }


        navigation(
            route = Route.HomeNavigation.route,
            startDestination = Route.HomeScreen.route
        ) {
            composable(route = Route.HomeScreen.route) {
                HomeScreen(navigateToDetail = {

                }, navigateToSearch = {
                    navController.navigate(Route.SearchScreen.route)
                })
            }

            composable(route = Route.SearchScreen.route) {
                SearchScreen(navController = navController, navigateToDetail = {
                    navController.navigate(Route.DetailVideoGameScreen.route)
                })
            }

            composable(route = Route.DetailVideoGameScreen.route) {
                DetailVideoGameScreen()
            }
        }

    }
}