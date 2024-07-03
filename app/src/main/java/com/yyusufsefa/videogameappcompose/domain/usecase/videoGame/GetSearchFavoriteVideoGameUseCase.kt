package com.yyusufsefa.videogameappcompose.domain.usecase.videoGame

import com.yyusufsefa.videogameappcompose.core.api.Resource
import com.yyusufsefa.videogameappcompose.core.api.apiFlow
import com.yyusufsefa.videogameappcompose.data.local.model.VideoGameFavoriteEntity
import com.yyusufsefa.videogameappcompose.domain.repository.VideoGameRepository
import kotlinx.coroutines.flow.Flow

class GetSearchFavoriteVideoGameUseCase(
    private val videoGameRepository: VideoGameRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<VideoGameFavoriteEntity>>> = apiFlow {
        videoGameRepository.searchFavoriteVideoGames(query)
    }
}