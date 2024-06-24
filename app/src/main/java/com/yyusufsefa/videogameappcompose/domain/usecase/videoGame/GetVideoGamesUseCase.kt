package com.yyusufsefa.videogameappcompose.domain.usecase.videoGame

import com.yyusufsefa.videogameappcompose.core.api.Resource
import com.yyusufsefa.videogameappcompose.core.api.apiFlow
import com.yyusufsefa.videogameappcompose.data.remote.dto.VideoGameResponse
import com.yyusufsefa.videogameappcompose.domain.repository.VideoGameRepository
import kotlinx.coroutines.flow.Flow

class GetVideoGamesUseCase(
    private val videoGameRepository: VideoGameRepository
) {
    operator fun invoke(page: Int, pageSize: Int): Flow<Resource<VideoGameResponse>> = apiFlow {
        videoGameRepository.getVideoGames(page, pageSize)
    }
}