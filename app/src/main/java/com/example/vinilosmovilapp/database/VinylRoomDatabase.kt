package com.example.vinilosmovilapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vinilosmovilapp.database.dao.AlbumsDao
import com.example.vinilosmovilapp.database.dao.ArtistsDao
import com.example.vinilosmovilapp.database.dao.CollectorsDao
import com.example.vinilosmovilapp.database.dao.CommentsDao
import com.example.vinilosmovilapp.models.Album
import com.example.vinilosmovilapp.models.Artist
import com.example.vinilosmovilapp.models.Collector
import com.example.vinilosmovilapp.models.Comment

@Database(entities = [Album::class, Artist::class, Collector::class, Comment::class], version = 1, exportSchema = false)
abstract class VinylRoomDatabase : RoomDatabase() {
    abstract fun albumsDao(): AlbumsDao
    abstract fun collectorsDao(): CollectorsDao
    abstract fun commentsDao(): CommentsDao
    abstract fun artistsDao(): ArtistsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: VinylRoomDatabase? = null

        fun getDatabase(context: Context): VinylRoomDatabase {
            // if the INSTANCE is not null, then return it, if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VinylRoomDatabase::class.java,
                    "vinyls_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}