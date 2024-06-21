package com.yyusufsefa.videogameappcompose.domain.usecase.onboarding

import com.yyusufsefa.videogameappcompose.domain.manager.DataStoreManager
import kotlinx.coroutines.flow.Flow

class GetOnBoardingShown(private val dataStoreManager: DataStoreManager) {
    operator fun invoke(): Flow<Boolean> {
        return dataStoreManager.getOnBoardingShown()
    }
}