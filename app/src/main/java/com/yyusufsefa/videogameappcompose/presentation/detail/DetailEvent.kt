package com.yyusufsefa.videogameappcompose.presentation.detail

import com.yyusufsefa.videogameappcompose.data.local.model.VideoGameFavoriteEntity

sealed class DetailEvent {
    data class GetDetailVideoGames(val id: Int) : DetailEvent()
    data class OnFavoriteVideoGame(
        val videoGameFavoriteEntity: VideoGameFavoriteEntity,
        val isFavorite: Boolean
    ) : DetailEvent()
}