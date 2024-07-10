package com.yyusufsefa.videogameappcompose.di

import com.yyusufsefa.videogameappcompose.data.repository.VideoGameRepositoryImpl
import com.yyusufsefa.videogameappcompose.domain.repository.VideoGameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
fun interface BinderModule {
    @Binds
    fun bindVideoGameRepository(impl: VideoGameRepositoryImpl): VideoGameRepository
}