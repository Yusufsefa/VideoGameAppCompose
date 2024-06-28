package com.yyusufsefa.videogameappcompose.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.videogameappcompose.core.api.Resource
import com.yyusufsefa.videogameappcompose.data.mapper.mapToVideoGame
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.VideoGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val videoGameUseCase: VideoGameUseCase) :
    ViewModel() {

    var homeState = mutableStateOf(HomeState())
        private set

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetVideoGames -> {
                getVideoGames(event.page, event.pageSize)
            }
        }

    }

    private fun getVideoGames(page: Int, pageSize: Int) {
        viewModelScope.launch {
            videoGameUseCase.getVideoGamesUseCase(page, pageSize).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        homeState.value = HomeState(isLoading = true)
                    }

                    is Resource.Success -> {
                        homeState.value =
                            HomeState(
                                videoGames = result.data.results.map {
                                    it.mapToVideoGame()
                                }.drop(3),
                                isLoading = false,
                                headerVideoGames = result.data.results.map { it.mapToVideoGame() }
                                    .take(3)
                            )
                    }

                    is Resource.Error -> {
                        homeState.value = HomeState(error = result.message, isLoading = false)
                    }
                }

            }.launchIn(viewModelScope)
        }
    }

}