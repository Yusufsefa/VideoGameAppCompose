package com.yyusufsefa.videogameappcompose.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.videogameappcompose.domain.usecase.onboarding.OnBoardingUseCase
import com.yyusufsefa.videogameappcompose.presentation.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val onBoardingUseCase: OnBoardingUseCase
) : ViewModel() {

    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination: StateFlow<String?> = _startDestination

    init {
        getOnBoardingShown()
    }

    private fun getOnBoardingShown() {
        viewModelScope.launch {
            onBoardingUseCase.getOnBoardingShown().collect { isShown ->
                if (isShown) {
                    _startDestination.value = Route.BottomNavigation.route
                } else {
                    _startDestination.value = Route.OnBoardingNavigation.route
                }
            }
        }
    }
}