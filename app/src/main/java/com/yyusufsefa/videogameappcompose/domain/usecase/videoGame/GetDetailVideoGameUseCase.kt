package com.yyusufsefa.videogameappcompose.domain.usecase.videoGame

import com.yyusufsefa.videogameappcompose.core.api.Resource
import com.yyusufsefa.videogameappcompose.core.api.apiFlow
import com.yyusufsefa.videogameappcompose.data.remote.dto.videoGameDetails.VideoGameDetailResponse
import com.yyusufsefa.videogameappcompose.domain.repository.VideoGameRepository
import kotlinx.coroutines.flow.Flow

class GetDetailVideoGameUseCase(
    private val videoGameRepository: VideoGameRepository
) {

    operator fun invoke(id: Int): Flow<Resource<VideoGameDetailResponse>> = apiFlow {
        videoGameRepository.getDetailVideoGame(id)
    }
}