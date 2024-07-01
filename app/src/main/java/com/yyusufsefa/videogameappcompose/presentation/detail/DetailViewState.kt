package com.yyusufsefa.videogameappcompose.presentation.detail

import com.yyusufsefa.videogameappcompose.domain.model.VideoGameDetail

data class DetailViewState(
    val isLoading: Boolean = false,
    val videoGameDetail: VideoGameDetail? = null,
    val isFavorite: Boolean = false,
    val platform: List<String> = emptyList(),
    val error: String = ""
)