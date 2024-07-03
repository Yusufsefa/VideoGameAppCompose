package com.yyusufsefa.videogameappcompose.di

import android.content.Context
import androidx.room.Room
import com.yyusufsefa.videogameappcompose.data.local.VideoGameDatabase
import com.yyusufsefa.videogameappcompose.data.local.dao.VideoGameDao
import com.yyusufsefa.videogameappcompose.data.remote.api.VideoGameApi
import com.yyusufsefa.videogameappcompose.data.repository.VideoGameRepositoryImpl
import com.yyusufsefa.videogameappcompose.domain.repository.VideoGameRepository
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.DeleteFavoriteVideoGameUseCase
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.GetAllFavoriteVideoGameUseCase
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.GetDetailVideoGameUseCase
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.GetSearchFavoriteVideoGameUseCase
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.GetSearchVideoGameUseCase
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.GetVideoGamesUseCase
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.InsertFavoriteVideoGameUseCase
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.VideoGameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideVideoGameRepository(api: VideoGameApi, dao: VideoGameDao): VideoGameRepository =
        VideoGameRepositoryImpl(api, dao)

    @Provides
    @Singleton
    fun provideVideoGameDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        VideoGameDatabase::class.java,
        "video_game_db"
    ).build()

    @Provides
    @Singleton
    fun provideNewsDao(
        videoGameDao: VideoGameDatabase
    ): VideoGameDao = videoGameDao.videoGameDao

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
    fun provideDeleteFavoriteVideoGameUseCase(repository: VideoGameRepository): DeleteFavoriteVideoGameUseCase {
        return DeleteFavoriteVideoGameUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideInsertFavoriteVideoGameUseCase(repository: VideoGameRepository): InsertFavoriteVideoGameUseCase {
        return InsertFavoriteVideoGameUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllFavoriteVideoGameUseCase(repository: VideoGameRepository): GetAllFavoriteVideoGameUseCase {
        return GetAllFavoriteVideoGameUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSearchFavoriteVideoGameUseCase(repository: VideoGameRepository): GetSearchFavoriteVideoGameUseCase {
        return GetSearchFavoriteVideoGameUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideVideoGameUseCase(
        getVideoGamesUseCase: GetVideoGamesUseCase,
        getDetailVideoGameUseCase: GetDetailVideoGameUseCase,
        getSearchVideoGameUseCase: GetSearchVideoGameUseCase,
        deleteFavoriteVideoGameUseCase: DeleteFavoriteVideoGameUseCase,
        insertFavoriteVideoGameUseCase: InsertFavoriteVideoGameUseCase,
        getAllFavoriteVideoGameUseCase: GetAllFavoriteVideoGameUseCase,
        getSearchFavoriteVideoGameUseCase: GetSearchFavoriteVideoGameUseCase

    ): VideoGameUseCase {
        return VideoGameUseCase(
            getVideoGamesUseCase,
            getDetailVideoGameUseCase,
            getSearchVideoGameUseCase,
            deleteFavoriteVideoGameUseCase,
            insertFavoriteVideoGameUseCase,
            getAllFavoriteVideoGameUseCase,
            getSearchFavoriteVideoGameUseCase
        )
    }
}