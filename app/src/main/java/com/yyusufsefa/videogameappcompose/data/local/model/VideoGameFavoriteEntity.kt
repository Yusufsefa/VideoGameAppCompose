package com.yyusufsefa.videogameappcompose.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video_game_favorites")
data class VideoGameFavoriteEntity(
    @PrimaryKey
    val id: Int,
    val imageUrl: String,
    val name: String,
    val desc: String,
    val rating: Double,
    val releaseDate: String,
    val platforms: List<String>,
    val isFavorite: Boolean
)