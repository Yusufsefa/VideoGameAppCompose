package com.yyusufsefa.videogameappcompose.domain.usecase.videoGame

import com.yyusufsefa.videogameappcompose.domain.repository.VideoGameRepository
import javax.inject.Inject

class DeleteFavoriteVideoGameUseCase @Inject constructor(
    private val videoGameRepository: VideoGameRepository
) {
    suspend operator fun invoke(id: Int) =
        videoGameRepository.deleteFavoriteVideoGameById(id)
}