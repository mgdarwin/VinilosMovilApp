package com.example.vinilosmovilapp.repositories

import android.app.Application
import com.example.vinilosmovilapp.models.Album
import com.example.vinilosmovilapp.network.NetworkServiceAdapter

class AlbumDetailRepository (val application: Application) {
    suspend fun refreshData(albumId: Int) : List<Album> {
        return (NetworkServiceAdapter.getInstance(application).getAlbum(albumId))
    }
}