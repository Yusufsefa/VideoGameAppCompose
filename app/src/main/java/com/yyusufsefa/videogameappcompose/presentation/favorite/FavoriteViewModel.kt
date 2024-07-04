package com.yyusufsefa.videogameappcompose.presentation.favorite

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
class FavoriteViewModel @Inject constructor(
    private val videoGameUseCase: VideoGameUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state: StateFlow<FavoriteState> = _state.asStateFlow()

    init {
        getFavoriteVideoGames()
    }


    fun getFavoriteVideoGames() {
        viewModelScope.launch {
            videoGameUseCase.getAllFavoriteVideoGameUseCase().onEach { result ->
                _state.value = when (result) {
                    is Resource.Loading -> _state.value.copy(isLoading = true)
                    is Resource.Success -> {
                        if (result.data.isEmpty()) {
                            _state.value.copy(
                                isLoading = false,
                                error = "No favorite video games found. Add some to your favorites!",
                                videoGame = emptyList()
                            )
                        } else {
                            _state.value.copy(
                                isLoading = false,
                                videoGame = result.data.map { it.mapToVideoGame() })
                        }
                    }

                    is Resource.Error ->
                        _state.value.copy(
                            isLoading = false,
                            error = result.message
                        )
                }

            }.catch { e ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown error",
                )
            }.launchIn(viewModelScope)
        }
    }
}