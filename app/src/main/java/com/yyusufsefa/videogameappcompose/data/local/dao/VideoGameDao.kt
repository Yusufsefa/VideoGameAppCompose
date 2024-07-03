package com.yyusufsefa.videogameappcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yyusufsefa.videogameappcompose.data.local.model.VideoGameFavoriteEntity

@Dao
interface VideoGameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteVideoGame: VideoGameFavoriteEntity)

    @Delete
    suspend fun delete(favoriteEntity: VideoGameFavoriteEntity)

    @Query("SELECT * FROM video_game_favorites")
    suspend fun getAllFavoriteVideoGames(): List<VideoGameFavoriteEntity>

    @Query("SELECT * FROM video_game_favorites WHERE name LIKE :searchQuery || '%'")
    suspend fun searchFavorite(searchQuery: String): List<VideoGameFavoriteEntity>

}