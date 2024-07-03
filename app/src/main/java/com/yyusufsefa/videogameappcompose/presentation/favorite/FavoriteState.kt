package com.yyusufsefa.videogameappcompose.presentation.favorite

import com.yyusufsefa.videogameappcompose.domain.model.VideoGame

data class FavoriteState(
    val isLoading: Boolean = false,
    val theMovies: List<VideoGame> = emptyList(),
    val error: String = ""
)