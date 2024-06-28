package com.yyusufsefa.videogameappcompose.presentation.home

import com.yyusufsefa.videogameappcompose.domain.model.VideoGame

data class HomeState(
    val isLoading: Boolean = false,
    val videoGames: List<VideoGame> = emptyList(),
    val headerVideoGames: List<VideoGame> = emptyList(),
    val error: String = ""
)