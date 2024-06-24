package com.yyusufsefa.videogameappcompose.presentation.home

import androidx.lifecycle.ViewModel
import com.yyusufsefa.videogameappcompose.domain.usecase.videoGame.VideoGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val videoGameUseCase: VideoGameUseCase) : ViewModel() {


}