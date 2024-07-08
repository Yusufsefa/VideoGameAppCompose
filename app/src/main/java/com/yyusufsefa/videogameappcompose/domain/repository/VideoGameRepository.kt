package com.yyusufsefa.videogameappcompose.domain.repository

import com.yyusufsefa.videogameappcompose.data.local.model.VideoGameFavoriteEntity
import com.yyusufsefa.videogameappcompose.data.remote.dto.videoGameDetails.VideoGameDetailResponse
import com.yyusufsefa.videogameappcompose.domain.model.VideoGame
import com.yyusufsefa.videogameappcompose.domain.model.VideoGameDetail

interface VideoGameRepository {
    suspend fun getAllVideoGame(page: Int, pageSize: Int): List<VideoGame>
    suspend fun getDetailVideoGame(id: Int): VideoGameDetail
    suspend fun searchVideoGames(query: String): List<VideoGame>

    suspend fun insertFavoriteVideoGame(favoriteEntity: VideoGameFavoriteEntity)
    suspend fun deleteFavoriteVideoGameById(id: Int)
    suspend fun getAllFavoriteVideoGames(): List<VideoGame>
    suspend fun searchFavoriteVideoGames(query: String): List<VideoGameFavoriteEntity>
    suspend fun getFavoriteVideoGameById(id: Int): VideoGameFavoriteEntity?
}