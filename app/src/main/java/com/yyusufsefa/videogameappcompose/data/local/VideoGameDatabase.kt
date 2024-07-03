package com.yyusufsefa.videogameappcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yyusufsefa.videogameappcompose.data.local.dao.VideoGameDao
import com.yyusufsefa.videogameappcompose.data.local.model.VideoGameFavoriteEntity

@Database(entities = [VideoGameFavoriteEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConvertor::class)
abstract class VideoGameDatabase : RoomDatabase() {
    abstract val videoGameDao: VideoGameDao
}