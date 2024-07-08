package com.yyusufsefa.videogameappcompose.domain.usecase.onboarding

import com.yyusufsefa.videogameappcompose.domain.manager.DataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOnBoardingShown @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    operator fun invoke(): Flow<Boolean> {
        return dataStoreManager.getOnBoardingShown()
    }
}