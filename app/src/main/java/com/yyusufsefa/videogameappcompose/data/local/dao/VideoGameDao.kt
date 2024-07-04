package com.yyusufsefa.videogameappcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yyusufsefa.videogameappcompose.data.local.model.VideoGameFavoriteEntity

@Dao
interface VideoGameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteVideoGame: VideoGameFavoriteEntity)

    @Query("DELETE FROM video_game_favorites WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM video_game_favorites")
    suspend fun getAllFavoriteVideoGames(): List<VideoGameFavoriteEntity>

    @Query("SELECT * FROM video_game_favorites WHERE name LIKE :searchQuery || '%'")
    suspend fun searchFavorite(searchQuery: String): List<VideoGameFavoriteEntity>

    @Query("SELECT * FROM video_game_favorites WHERE id = :id")
    suspend fun getFavoriteVideoGameById(id: Int): VideoGameFavoriteEntity?

}