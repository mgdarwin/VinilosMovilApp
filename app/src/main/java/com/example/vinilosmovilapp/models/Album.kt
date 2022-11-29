package com.example.vinilosmovilapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
@Entity(tableName = "albums_table")
data class Album(
    @PrimaryKey val albumId: Int,
    val name: String,
    val cover: String?,
    val releaseDate: String?,
    val description: String?,
    val genre: String?,
    val recordLabel: String?,
    val tracks: List<Track>,
) : Parcelable