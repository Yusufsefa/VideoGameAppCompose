package com.yyusufsefa.videogameappcompose.domain.usecase.videoGame

import javax.inject.Inject

data class VideoGameDetailUseCase @Inject constructor(
    val getDetailVideoGameUseCase: GetDetailVideoGameUseCase,
    val deleteFavoriteVideoGameUseCase: DeleteFavoriteVideoGameUseCase,
    val insertFavoriteVideoGameUseCase: InsertFavoriteVideoGameUseCase,
    val getFavoriteVideoGameByIdUseCase: GetFavoriteVideoGameByIdUseCase
)