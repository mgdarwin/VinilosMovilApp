package com.example.vinilosmovilapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Track(
    val name: String,
    val duration: String
) : Parcelable