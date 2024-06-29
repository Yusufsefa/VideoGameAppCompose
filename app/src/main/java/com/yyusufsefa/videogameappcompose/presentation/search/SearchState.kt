package com.yyusufsefa.videogameappcompose.presentation.search

import com.yyusufsefa.videogameappcompose.domain.model.VideoGame

data class SearchState(
    val isLoading: Boolean = false,
    val searchVideoGames: List<VideoGame> = emptyList(),
    val query: String = "",
    val error: String = ""
)