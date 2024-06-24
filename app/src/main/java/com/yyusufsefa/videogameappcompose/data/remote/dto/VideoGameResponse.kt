package com.yyusufsefa.videogameappcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class VideoGameResponse(
    val count: Int,
    val description: String,
    val filters: Filters,
    val next: String,
    val nofollow: Boolean,
    val nofollow_collections: List<String>,
    val noindex: Boolean,
    val previous: Any,
    @SerializedName("results")
    val results: List<VideoGameResult>,
    val seo_description: String,
    val seo_h1: String,
    val seo_keywords: String,
    val seo_title: String
)