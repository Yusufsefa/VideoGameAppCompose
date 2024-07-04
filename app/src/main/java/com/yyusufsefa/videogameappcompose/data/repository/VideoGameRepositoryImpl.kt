package com.yyusufsefa.videogameappcompose.data.repository

import com.yyusufsefa.videogameappcompose.data.local.dao.VideoGameDao
import com.yyusufsefa.videogameappcompose.data.local.model.VideoGameFavoriteEntity
import com.yyusufsefa.videogameappcompose.data.remote.api.VideoGameApi
import com.yyusufsefa.videogameappcompose.data.remote.dto.VideoGameResponse
import com.yyusufsefa.videogameappcompose.data.remote.dto.videoGameDetails.VideoGameDetailResponse
import com.yyusufsefa.videogameappcompose.domain.repository.VideoGameRepository

class VideoGameRepositoryImpl(
    private val videoGameApi: VideoGameApi,
    private val videoGameDao: VideoGameDao
) : VideoGameRepository {
    override suspend fun getVideoGames(page: Int, pageSize: Int): VideoGameResponse =
        videoGameApi.getVideoGames(page, pageSize)

    override suspend fun getDetailVideoGame(id: Int): VideoGameDetailResponse =
        videoGameApi.getDetailVideoGame(id)

    override suspend fun searchVideoGames(query: String): VideoGameResponse =
        videoGameApi.searchVideoGames(search = query)

    override suspend fun insertFavoriteVideoGame(favoriteEntity: VideoGameFavoriteEntity) {
        videoGameDao.insert(favoriteEntity)
    }

    override suspend fun deleteFavoriteVideoGameById(id: Int) {
        videoGameDao.deleteById(id)
    }

    override suspend fun getAllFavoriteVideoGames(): List<VideoGameFavoriteEntity> =
        videoGameDao.getAllFavoriteVideoGames()


    override suspend fun searchFavoriteVideoGames(query: String): List<VideoGameFavoriteEntity> =
        videoGameDao.searchFavorite(query)

    override suspend fun getFavoriteVideoGameById(id: Int): VideoGameFavoriteEntity? =
        videoGameDao.getFavoriteVideoGameById(id)


}