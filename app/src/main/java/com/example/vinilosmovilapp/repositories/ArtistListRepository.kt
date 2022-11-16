package com.example.vinilosmovilapp.repositories

import android.app.Application
import android.util.Log
import com.example.vinilosmovilapp.models.Artist
import com.example.vinilosmovilapp.network.CacheManager
import com.example.vinilosmovilapp.network.NetworkServiceAdapter

class ArtistListRepository (val application: Application){
    suspend fun refreshData() : List<Artist> {
        var potentialResp =
            CacheManager.getInstance(application.applicationContext).getArtists()
        return(NetworkServiceAdapter.getInstance(application).getArtists())
    }
}