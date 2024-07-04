package com.yyusufsefa.videogameappcompose.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.videogameappcompose.core.api.Resource
import com.yyusufsefa.videogameappcompose.data.mapper.mapToVideoGame
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.VideoGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val videoGameUseCase: VideoGameUseCase) :
    ViewModel() {

    private val _searchState = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState> = _searchState.asStateFlow()

    private var searchJob: Job? = null

    init {
        if (_searchState.value.query.isNotEmpty()) {
            onEvent(SearchEvent.GetSearchVideoGames(_searchState.value.query))
        }
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.GetSearchVideoGames -> {
                getSearchVideoGames(event.query)
            }

            is SearchEvent.UpdateQuery -> {
                _searchState.value = _searchState.value.copy(query = event.query)
            }
        }
    }

    private fun getSearchVideoGames(query: String) {
        _searchState.value = _searchState.value.copy(isLoading = true)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            videoGameUseCase.searchVideoGameUseCase(query)
                .onEach { result ->
                    _searchState.value = when (result) {
                        is Resource.Loading -> _searchState.value.copy(isLoading = true)
                        is Resource.Success -> {
                            if (result.data.results.isEmpty()) {
                                _searchState.value.copy(
                                    isLoading = false,
                                    error = if (query.isNotEmpty()) "No results found" else "",
                                    searchVideoGames = emptyList()
                                )
                            } else {
                                _searchState.value.copy(
                                    searchVideoGames = result.data.results.map { it.mapToVideoGame() },
                                    isLoading = false,
                                    error = ""
                                )
                            }
                        }

                        is Resource.Error -> _searchState.value.copy(
                            error = result.message,
                            isLoading = false,
                            searchVideoGames = emptyList()
                        )
                    }
                }
                .catch { e ->
                    _searchState.value = _searchState.value.copy(
                        error = e.message ?: "Unknown error",
                        isLoading = false,
                        searchVideoGames = emptyList()
                    )
                }
                .launchIn(viewModelScope)
        }
    }
}


