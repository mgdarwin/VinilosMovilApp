package com.example.vinilosmovilapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
@Entity(tableName = "tracks_table")
data class Track(
    @PrimaryKey(autoGenerate = true)
    val name: String,
    val duration: String
) : Parcelable