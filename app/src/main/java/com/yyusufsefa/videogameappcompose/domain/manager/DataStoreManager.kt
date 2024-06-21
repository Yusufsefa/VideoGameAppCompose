package com.yyusufsefa.videogameappcompose.domain.manager

import kotlinx.coroutines.flow.Flow

interface DataStoreManager {

    suspend fun saveOnBoardingShown(isShown: Boolean)

    fun getOnBoardingShown(): Flow<Boolean>
}