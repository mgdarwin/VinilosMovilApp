package com.example.vinilosmovilapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vinilosmovilapp.models.Artist

@Dao
interface ArtistsDao {
    @Query("SELECT * FROM artists_table")
    fun getArtists():List<Artist>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(artist: Artist)

    @Query("DELETE FROM artists_table")
    suspend fun deleteAll():Int

}