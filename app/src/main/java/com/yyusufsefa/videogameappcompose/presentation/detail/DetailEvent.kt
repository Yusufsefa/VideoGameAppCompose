package com.yyusufsefa.videogameappcompose.presentation.detail

sealed class DetailEvent {
    data class GetDetailVideoGames(val id: Int) : DetailEvent()
}