package com.yyusufsefa.videogameappcompose.domain.usecase.videoGame

import com.yyusufsefa.videogameappcompose.core.api.Resource
import com.yyusufsefa.videogameappcompose.core.api.apiFlow
import com.yyusufsefa.videogameappcompose.domain.model.VideoGame
import com.yyusufsefa.videogameappcompose.domain.repository.VideoGameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoriteVideoGameUseCase @Inject constructor(
    private val videoGameRepository: VideoGameRepository
) {
    operator fun invoke(): Flow<Resource<List<VideoGame>>> = apiFlow {
        videoGameRepository.getAllFavoriteVideoGames()
    }
}