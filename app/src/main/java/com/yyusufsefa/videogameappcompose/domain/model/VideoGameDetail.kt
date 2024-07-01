package com.yyusufsefa.videogameappcompose.domain.model

data class VideoGameDetail(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val desc: String,
    val rating: Double,
    val releaseDate: String,
    val platforms: List<String>
)