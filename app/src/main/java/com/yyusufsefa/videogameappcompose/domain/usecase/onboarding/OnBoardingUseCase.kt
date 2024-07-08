package com.yyusufsefa.videogameappcompose.domain.usecase.onboarding

import javax.inject.Inject

data class OnBoardingUseCase @Inject constructor(
    val saveOnBoardingShown: SaveOnBoardingShown,
    val getOnBoardingShown: GetOnBoardingShown
)