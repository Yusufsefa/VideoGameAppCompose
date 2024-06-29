package com.yyusufsefa.videogameappcompose.data.repository

import com.yyusufsefa.videogameappcompose.data.remote.api.VideoGameApi
import com.yyusufsefa.videogameappcompose.data.remote.dto.VideoGameResponse
import com.yyusufsefa.videogameappcompose.data.remote.dto.videoGameDetails.VideoGameDetailResponse
import com.yyusufsefa.videogameappcompose.domain.repository.VideoGameRepository

class VideoGameRepositoryImpl(private val videoGameApi: VideoGameApi) :
    VideoGameRepository {
    override suspend fun getVideoGames(page: Int, pageSize: Int): VideoGameResponse =
        videoGameApi.getVideoGames(page, pageSize)

    override suspend fun getDetailVideoGame(id: Int): VideoGameDetailResponse =
        videoGameApi.getDetailVideoGame(id)

    override suspend fun searchVideoGames(query: String): VideoGameResponse =
        videoGameApi.searchVideoGames(search = query)


}