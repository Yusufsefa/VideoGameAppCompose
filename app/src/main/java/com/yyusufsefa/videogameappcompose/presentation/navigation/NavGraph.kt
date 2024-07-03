package com.yyusufsefa.videogameappcompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.yyusufsefa.videogameappcompose.presentation.onboarding.OnBoardingScreen
import com.yyusufsefa.videogameappcompose.presentation.onboarding.OnBoardingViewModel


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
            route = Route.BottomNavigation.route,
            startDestination = Route.BottomMenuBar.route
        ) {
            composable(route = Route.BottomMenuBar.route) {
                BottomMenuBarNavigator()
            }
        }

    }
}