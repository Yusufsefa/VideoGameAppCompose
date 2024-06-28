package com.yyusufsefa.videogameappcompose.presentation.home

sealed class HomeEvent {
    data class GetVideoGames(val page: Int, val pageSize: Int) : HomeEvent()
}