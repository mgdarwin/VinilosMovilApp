package com.example.vinilosmovilapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Artist(
    @PrimaryKey val artistId: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String
)