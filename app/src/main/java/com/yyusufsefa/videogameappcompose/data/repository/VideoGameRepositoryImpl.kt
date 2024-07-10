package com.yyusufsefa.videogameappcompose.data.repository

import com.yyusufsefa.videogameappcompose.data.local.dao.VideoGameDao
import com.yyusufsefa.videogameappcompose.data.local.model.VideoGameFavoriteEntity
import com.yyusufsefa.videogameappcompose.data.mapper.mapToVideoGame
import com.yyusufsefa.videogameappcompose.data.mapper.mapToVideoGameDetail
import com.yyusufsefa.videogameappcompose.data.remote.api.VideoGameApi
import com.yyusufsefa.videogameappcompose.domain.model.VideoGame
import com.yyusufsefa.videogameappcompose.domain.model.VideoGameDetail
import com.yyusufsefa.videogameappcompose.domain.repository.VideoGameRepository
import javax.inject.Inject

class VideoGameRepositoryImpl @Inject constructor(
    private val videoGameApi: VideoGameApi,
    private val videoGameDao: VideoGameDao
) : VideoGameRepository {

    override suspend fun getAllVideoGame(page: Int, pageSize: Int): List<VideoGame> =
        videoGameApi.getVideoGames(page, pageSize).results.map { it.mapToVideoGame() }

    override suspend fun getDetailVideoGame(id: Int): VideoGameDetail =
        videoGameApi.getDetailVideoGame(id).mapToVideoGameDetail()

    override suspend fun searchVideoGames(query: String): List<VideoGame> =
        videoGameApi.searchVideoGames(search = query).results.map { it.mapToVideoGame() }

    override suspend fun insertFavoriteVideoGame(favoriteEntity: VideoGameFavoriteEntity) {
        videoGameDao.insert(favoriteEntity)
    }

    override suspend fun deleteFavoriteVideoGameById(id: Int) {
        videoGameDao.deleteById(id)
    }

    override suspend fun getAllFavoriteVideoGames(): List<VideoGame> =
        videoGameDao.getAllFavoriteVideoGames().map { it.mapToVideoGame() }


    override suspend fun searchFavoriteVideoGames(query: String): List<VideoGameFavoriteEntity> =
        videoGameDao.searchFavorite(query)

    override suspend fun getFavoriteVideoGameById(id: Int): VideoGameFavoriteEntity? =
        videoGameDao.getFavoriteVideoGameById(id)


}