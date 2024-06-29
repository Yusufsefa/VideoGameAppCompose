package com.yyusufsefa.videogameappcompose.di

import com.yyusufsefa.videogameappcompose.data.remote.api.VideoGameApi
import com.yyusufsefa.videogameappcompose.data.repository.VideoGameRepositoryImpl
import com.yyusufsefa.videogameappcompose.domain.repository.VideoGameRepository
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.GetDetailVideoGameUseCase
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.GetSearchVideoGameUseCase
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.GetVideoGamesUseCase
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.VideoGameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideVideoGameRepository(api: VideoGameApi): VideoGameRepository =
        VideoGameRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideGetVideoGameUseCase(repository: VideoGameRepository): GetVideoGamesUseCase {
        return GetVideoGamesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetDetailVideoGameUseCase(repository: VideoGameRepository): GetDetailVideoGameUseCase {
        return GetDetailVideoGameUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSearchVideoGameUseCase(repository: VideoGameRepository): GetSearchVideoGameUseCase {
        return GetSearchVideoGameUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideVideoGameUseCase(
        getVideoGamesUseCase: GetVideoGamesUseCase,
        getDetailVideoGameUseCase: GetDetailVideoGameUseCase,
        getSearchVideoGameUseCase: GetSearchVideoGameUseCase
    ): VideoGameUseCase {
        return VideoGameUseCase(
            getVideoGamesUseCase,
            getDetailVideoGameUseCase,
            getSearchVideoGameUseCase
        )
    }
}