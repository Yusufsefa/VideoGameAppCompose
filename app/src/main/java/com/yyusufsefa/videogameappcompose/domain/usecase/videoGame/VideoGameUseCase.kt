package com.yyusufsefa.videogameappcompose.domain.usecase.videoGame

data class VideoGameUseCase(
    val getVideoGamesUseCase: GetVideoGamesUseCase,
    val getDetailVideoGameUseCase: GetDetailVideoGameUseCase,
    val searchVideoGameUseCase: GetSearchVideoGameUseCase,
    val deleteFavoriteVideoGameUseCase: DeleteFavoriteVideoGameUseCase,
    val insertFavoriteVideoGameUseCase: InsertFavoriteVideoGameUseCase,
    val getAllFavoriteVideoGameUseCase: GetAllFavoriteVideoGameUseCase,
    val getSearchFavoriteVideoGameUseCase: GetSearchFavoriteVideoGameUseCase
)