package com.yyusufsefa.videogameappcompose.domain.usecase.videoGame

import com.yyusufsefa.videogameappcompose.core.api.Resource
import com.yyusufsefa.videogameappcompose.core.api.apiFlow
import com.yyusufsefa.videogameappcompose.data.remote.dto.videoGameDetails.VideoGameDetailResponse
import com.yyusufsefa.videogameappcompose.domain.model.VideoGameDetail
import com.yyusufsefa.videogameappcompose.domain.repository.VideoGameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailVideoGameUseCase @Inject constructor(
    private val videoGameRepository: VideoGameRepository
) {
    operator fun invoke(id: Int): Flow<Resource<VideoGameDetail>> = apiFlow {
        videoGameRepository.getDetailVideoGame(id)
    }
}