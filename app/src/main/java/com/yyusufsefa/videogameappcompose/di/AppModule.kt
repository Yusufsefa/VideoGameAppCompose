package com.yyusufsefa.videogameappcompose.di

import android.content.Context
import androidx.room.Room
import com.yyusufsefa.videogameappcompose.data.local.TypeConvertor
import com.yyusufsefa.videogameappcompose.data.local.VideoGameDatabase
import com.yyusufsefa.videogameappcompose.data.local.dao.VideoGameDao
import com.yyusufsefa.videogameappcompose.data.remote.api.VideoGameApi
import com.yyusufsefa.videogameappcompose.data.repository.VideoGameRepositoryImpl
import com.yyusufsefa.videogameappcompose.domain.repository.VideoGameRepository
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
    ).addTypeConverter(TypeConvertor())
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideNewsDao(
        videoGameDao: VideoGameDatabase
    ): VideoGameDao = videoGameDao.videoGameDao
}