package com.yyusufsefa.videogameappcompose.domain.usecase.onboarding

import com.yyusufsefa.videogameappcompose.domain.manager.DataStoreManager
import javax.inject.Inject

class SaveOnBoardingShown @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    suspend operator fun invoke(isShown: Boolean) {
        dataStoreManager.saveOnBoardingShown(isShown)
    }
}