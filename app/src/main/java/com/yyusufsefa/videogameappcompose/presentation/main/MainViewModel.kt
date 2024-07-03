package com.yyusufsefa.videogameappcompose.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.videogameappcompose.domain.usecase.onboarding.OnBoardingUseCase
import com.yyusufsefa.videogameappcompose.presentation.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val onBoardingUseCase: OnBoardingUseCase
) : ViewModel() {

    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination: StateFlow<String?> = _startDestination

    init {
        onBoardingUseCase.getOnBoardingShown().onEach { isShown ->
            if (isShown) {
                _startDestination.value = Route.BottomNavigation.route
            } else {
                _startDestination.value = Route.OnBoardingNavigation.route
            }
        }.launchIn(viewModelScope)
    }
}