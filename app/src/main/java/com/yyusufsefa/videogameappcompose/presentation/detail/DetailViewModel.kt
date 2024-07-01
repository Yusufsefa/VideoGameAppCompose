package com.yyusufsefa.videogameappcompose.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.videogameappcompose.core.api.Resource
import com.yyusufsefa.videogameappcompose.data.mapper.mapToVideoGameDetail
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
class DetailViewModel @Inject constructor(
    private val videoGameUseCase: VideoGameUseCase
) : ViewModel() {

    private val _detailState = MutableStateFlow(DetailViewState())
    val detailState: StateFlow<DetailViewState> = _detailState.asStateFlow()

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.GetDetailVideoGames -> {
                getDetailVideoGames(event.id)
            }
        }
    }

    private fun getDetailVideoGames(id: Int) {
        viewModelScope.launch {
            videoGameUseCase.getDetailVideoGameUseCase(id).onEach { result ->
                _detailState.value = when (result) {
                    is Resource.Loading -> _detailState.value.copy(isLoading = true)
                    is Resource.Success -> {
                        _detailState.value.copy(
                            videoGameDetail = result.data.mapToVideoGameDetail(),
                            isLoading = false
                        )
                    }

                    is Resource.Error -> _detailState.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }.catch { e ->
                _detailState.value = _detailState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }.launchIn(viewModelScope)
        }
    }
}