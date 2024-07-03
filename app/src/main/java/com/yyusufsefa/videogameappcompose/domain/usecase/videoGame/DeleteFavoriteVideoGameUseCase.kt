package com.yyusufsefa.videogameappcompose.domain.usecase.videoGame

import com.yyusufsefa.videogameappcompose.data.local.model.VideoGameFavoriteEntity
import com.yyusufsefa.videogameappcompose.domain.repository.VideoGameRepository

class DeleteFavoriteVideoGameUseCase(
    private val videoGameRepository: VideoGameRepository
) {
    suspend operator fun invoke(favoriteEntity: VideoGameFavoriteEntity) =
        videoGameRepository.deleteFavoriteVideoGame(favoriteEntity)
}