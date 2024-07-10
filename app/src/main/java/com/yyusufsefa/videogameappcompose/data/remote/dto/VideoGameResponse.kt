package com.yyusufsefa.videogameappcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class VideoGameResponse(
    val count: Int?,
    val description: String?,
    val next: String?,
    @SerializedName("results")
    val results: List<VideoGameResult>,
)