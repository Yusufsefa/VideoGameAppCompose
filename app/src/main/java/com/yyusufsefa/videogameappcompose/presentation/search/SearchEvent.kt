package com.yyusufsefa.videogameappcompose.presentation.search

sealed class SearchEvent {
    data class GetSearchVideoGames(val query: String) : SearchEvent()
    data class UpdateQuery(val query: String) : SearchEvent()
}
