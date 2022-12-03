package com.example.vinilosmovilapp.repositories

import android.app.Application
import com.example.vinilosmovilapp.models.Album
import com.example.vinilosmovilapp.network.NetworkServiceAdapter

class AlbumAddRepository (val application: Application) {

    suspend fun addAlbum(album:Album) {
        NetworkServiceAdapter.getInstance(application).addAlbum(album)
    }
}