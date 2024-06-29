package com.yyusufsefa.videogameappcompose.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.videogameappcompose.core.api.Resource
import com.yyusufsefa.videogameappcompose.data.mapper.mapToVideoGame
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.VideoGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val videoGameUseCase: VideoGameUseCase) :
    ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetVideoGames -> {
                getVideoGames(event.page, event.pageSize)
            }
        }
    }

    private fun getVideoGames(page: Int, pageSize: Int) {
        viewModelScope.launch {
            videoGameUseCase.getVideoGamesUseCase(page, pageSize)
                .onEach { result ->
                    _homeState.value = when (result) {
                        is Resource.Loading -> _homeState.value.copy(isLoading = true)
                        is Resource.Success -> _homeState.value.copy(
                            videoGames = result.data.results.map { it.mapToVideoGame() }.drop(3),
                            isLoading = false,
                            headerVideoGames = result.data.results.map { it.mapToVideoGame() }
                                .take(3)
                        )

                        is Resource.Error -> _homeState.value.copy(
                            error = result.message,
                            isLoading = false
                        )
                    }
                }
                .catch { e ->
                    _homeState.value = _homeState.value.copy(
                        error = e.message ?: "Unknown error",
                        isLoading = false
                    )
                }
                .launchIn(viewModelScope)
        }
    }

}
