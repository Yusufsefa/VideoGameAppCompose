package com.yyusufsefa.videogameappcompose.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.videogameappcompose.domain.usecase.onboarding.OnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val onBoardingUseCase: OnBoardingUseCase
) : ViewModel() {

    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SaveOnBoardingState -> {
                saveOnBoardingState(event.isShown)
            }
        }
    }

    private fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch {
            onBoardingUseCase.saveOnBoardingShown(isShown = completed)
        }
    }
}