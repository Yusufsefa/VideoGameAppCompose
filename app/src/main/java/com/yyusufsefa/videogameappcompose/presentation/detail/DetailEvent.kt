package com.yyusufsefa.videogameappcompose.presentation.detail

import com.yyusufsefa.videogameappcompose.data.local.model.VideoGameFavoriteEntity
import com.yyusufsefa.videogameappcompose.domain.model.VideoGame
import com.yyusufsefa.videogameappcompose.domain.model.VideoGameDetail

sealed class DetailEvent {
    data class GetDetailVideoGames(val id: Int) : DetailEvent()
    data class OnFavoriteVideoGame(
        val videoGameDetail: VideoGameDetail,
        val isFavorite: Boolean
    ) : DetailEvent()
}