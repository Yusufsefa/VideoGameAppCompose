package com.yyusufsefa.videogameappcompose.presentation.search

import com.yyusufsefa.videogameappcompose.domain.model.VideoGame

data class SearchState(
    val query: String = "",
    val isLoading: Boolean = false,
    val searchVideoGames: List<VideoGame> = emptyList(),
    val error: String = ""
)
