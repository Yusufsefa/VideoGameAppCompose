package com.yyusufsefa.videogameappcompose.presentation.onboarding

sealed class OnBoardingEvent {
    data class SaveOnBoardingState(val isShown: Boolean) : OnBoardingEvent()
}