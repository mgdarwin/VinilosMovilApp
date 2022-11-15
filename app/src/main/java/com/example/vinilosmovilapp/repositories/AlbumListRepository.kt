package com.example.vinilosmovilapp.repositories

import android.app.Application
import com.example.vinilosmovilapp.models.Album
import com.example.vinilosmovilapp.network.NetworkServiceAdapter

class AlbumListRepository (val application: Application) {
    suspend fun refreshData() : List<Album> {
        return(NetworkServiceAdapter.getInstance(application).getAlbums())
    }
}