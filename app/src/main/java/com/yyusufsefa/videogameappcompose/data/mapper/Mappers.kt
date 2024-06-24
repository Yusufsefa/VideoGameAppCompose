package com.yyusufsefa.videogameappcompose.data.mapper

import com.yyusufsefa.videogameappcompose.data.remote.dto.VideoGameResult
import com.yyusufsefa.videogameappcompose.data.remote.dto.videoGameDetails.VideoGameDetailResponse
import com.yyusufsefa.videogameappcompose.domain.model.VideoGame
import com.yyusufsefa.videogameappcompose.domain.model.VideoGameDetail


fun VideoGameResult.mapToVideoGame(): VideoGame {
    return VideoGame(
        id = id,
        name = name,
        imageUrl = background_image,
        rating = rating
    )
}


fun VideoGameDetailResponse.mapToVideoGameDetail(): VideoGameDetail {
    return VideoGameDetail(
        id = id,
        name = name,
        imageUrl = background_image,
        rating = rating,
        releaseDate = released,
        desc = description,
        platform = platforms.map { it.platform }
    )
}