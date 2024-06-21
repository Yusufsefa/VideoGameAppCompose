package com.yyusufsefa.videogameappcompose.di

import android.content.Context
import com.yyusufsefa.videogameappcompose.data.manager.DataStoreManagerImpl
import com.yyusufsefa.videogameappcompose.domain.manager.DataStoreManager
import com.yyusufsefa.videogameappcompose.domain.usecase.onboarding.GetOnBoardingShown
import com.yyusufsefa.videogameappcompose.domain.usecase.onboarding.OnBoardingUseCase
import com.yyusufsefa.videogameappcompose.domain.usecase.onboarding.SaveOnBoardingShown
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStoreManager(
        @ApplicationContext context: Context
    ): DataStoreManager {
        return DataStoreManagerImpl(context)
    }

    @Provides
    @Singleton
    fun provideSaveOnBoardingShownUseCase(
        dataStoreManager: DataStoreManager
    ): SaveOnBoardingShown {
        return SaveOnBoardingShown(dataStoreManager)
    }

    @Provides
    @Singleton
    fun provideGetOnBoardingShownUseCase(
        dataStoreManager: DataStoreManager
    ): GetOnBoardingShown {
        return GetOnBoardingShown(dataStoreManager)
    }

    @Provides
    @Singleton
    fun provideOnBoardingUseCase(
        saveOnBoardingShown: SaveOnBoardingShown,
        getOnBoardingShown: GetOnBoardingShown
    ): OnBoardingUseCase {
        return OnBoardingUseCase(
            saveOnBoardingShown = saveOnBoardingShown,
            getOnBoardingShown = getOnBoardingShown
        )
    }
}