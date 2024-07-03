package com.yyusufsefa.videogameappcompose.domain.repository

import com.yyusufsefa.videogameappcompose.data.local.model.VideoGameFavoriteEntity
import com.yyusufsefa.videogameappcompose.data.remote.dto.VideoGameResponse
import com.yyusufsefa.videogameappcompose.data.remote.dto.videoGameDetails.VideoGameDetailResponse

interface VideoGameRepository {

    suspend fun getVideoGames(page: Int, pageSize: Int): VideoGameResponse
    suspend fun getDetailVideoGame(id: Int): VideoGameDetailResponse
    suspend fun searchVideoGames(query: String): VideoGameResponse

    suspend fun insertFavoriteVideoGame(favoriteEntity: VideoGameFavoriteEntity)
    suspend fun deleteFavoriteVideoGame(favoriteEntity: VideoGameFavoriteEntity)
    suspend fun getAllFavoriteVideoGames(): List<VideoGameFavoriteEntity>
    suspend fun searchFavoriteVideoGames(query: String): List<VideoGameFavoriteEntity>
}