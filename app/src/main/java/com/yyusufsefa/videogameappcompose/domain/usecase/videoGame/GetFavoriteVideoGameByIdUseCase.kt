package com.yyusufsefa.videogameappcompose.domain.usecase.videoGame

import com.yyusufsefa.videogameappcompose.domain.repository.VideoGameRepository
import javax.inject.Inject

class GetFavoriteVideoGameByIdUseCase @Inject constructor(
    private val videoGameRepository: VideoGameRepository
) {
    suspend operator fun invoke(id: Int) = videoGameRepository.getFavoriteVideoGameById(id)
}