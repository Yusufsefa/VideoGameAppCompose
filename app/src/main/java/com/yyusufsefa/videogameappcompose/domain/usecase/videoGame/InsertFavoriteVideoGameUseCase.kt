package com.yyusufsefa.videogameappcompose.domain.usecase.videoGame

import com.yyusufsefa.videogameappcompose.data.local.model.VideoGameFavoriteEntity
import com.yyusufsefa.videogameappcompose.domain.repository.VideoGameRepository
import javax.inject.Inject

class InsertFavoriteVideoGameUseCase @Inject constructor(
    private val videoGameRepository: VideoGameRepository
) {
    suspend operator fun invoke(favoriteEntity: VideoGameFavoriteEntity) {
        videoGameRepository.insertFavoriteVideoGame(favoriteEntity)
    }
}