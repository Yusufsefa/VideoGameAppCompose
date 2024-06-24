package com.yyusufsefa.videogameappcompose.domain.model

import com.yyusufsefa.videogameappcompose.data.remote.dto.videoGameDetails.PlatformXXX

data class VideoGameDetail(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val desc: String,
    val rating: Double,
    val releaseDate: String,
    val platform: List<PlatformXXX>
)