package com.yyusufsefa.videogameappcompose.data.remote.api

import com.yyusufsefa.videogameappcompose.core.constant.Constants.KEY
import com.yyusufsefa.videogameappcompose.data.remote.dto.VideoGameResponse
import com.yyusufsefa.videogameappcompose.data.remote.dto.videoGameDetails.VideoGameDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VideoGameApi {

    @GET("games")
    suspend fun getVideoGames(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("key") key: String = KEY
    ): VideoGameResponse

    @GET("games/{id}")
    suspend fun getDetailVideoGame(
        @Path("id") id: Int,
        @Query("key") key: String = KEY
    ): VideoGameDetailResponse
}