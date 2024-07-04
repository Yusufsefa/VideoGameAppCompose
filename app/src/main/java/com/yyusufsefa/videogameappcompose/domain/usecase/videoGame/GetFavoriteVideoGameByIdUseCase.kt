package com.yyusufsefa.videogameappcompose.domain.usecase.videoGame

import com.yyusufsefa.videogameappcompose.domain.repository.VideoGameRepository

class GetFavoriteVideoGameByIdUseCase constructor(
    private val videoGameRepository: VideoGameRepository
) {
    suspend operator fun invoke(id: Int) = videoGameRepository.getFavoriteVideoGameById(id)
}