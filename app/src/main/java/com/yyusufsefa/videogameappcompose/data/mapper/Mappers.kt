package com.yyusufsefa.videogameappcompose.data.mapper

import com.yyusufsefa.videogameappcompose.data.local.model.VideoGameFavoriteEntity
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
        platforms = parent_platforms.map { it.platform.name }
    )
}

fun VideoGameFavoriteEntity.mapToVideoGame(): VideoGame {
    return VideoGame(
        id = id,
        name = name,
        imageUrl = imageUrl,
        rating = rating
    )
}

fun VideoGameDetail.mapToVideoGameEntity(isFavorite: Boolean): VideoGameFavoriteEntity {
    return VideoGameFavoriteEntity(
        id = id,
        name = name,
        imageUrl = imageUrl,
        rating = rating,
        releaseDate = releaseDate,
        desc = desc,
        platforms = platforms,
        isFavorite = isFavorite
    )

}