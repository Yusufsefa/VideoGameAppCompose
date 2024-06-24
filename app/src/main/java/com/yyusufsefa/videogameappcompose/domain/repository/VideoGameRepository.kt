package com.yyusufsefa.videogameappcompose.domain.repository

import com.yyusufsefa.videogameappcompose.data.remote.dto.VideoGameResponse
import com.yyusufsefa.videogameappcompose.data.remote.dto.videoGameDetails.VideoGameDetailResponse

interface VideoGameRepository {

    suspend fun getVideoGames(page: Int, pageSize: Int): VideoGameResponse
    suspend fun getDetailVideoGame(id: Int): VideoGameDetailResponse
}