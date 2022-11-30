package com.example.vinilosmovilapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Collector(
    @PrimaryKey val collectorId: Int,
    val name: String,
    val telephone: String,
    val email: String
)