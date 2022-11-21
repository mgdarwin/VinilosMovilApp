package com.example.vinilosmovilapp.repositories

import android.app.Application
import android.util.Log
import com.example.vinilosmovilapp.models.Artist
import com.example.vinilosmovilapp.network.CacheManager
import com.example.vinilosmovilapp.network.NetworkServiceAdapter

class ArtistDetailRepository (val application: Application) {

    suspend fun refreshData(artistId: Int): List<Artist> {
        var potentialResp =
            CacheManager.getInstance(application.applicationContext).getArtist(artistId)
        if (potentialResp.isEmpty()) {
            Log.d("Cache decision", "get artist detail from network")
            var artists = NetworkServiceAdapter.getInstance(application).getArtist(artistId)
            CacheManager.getInstance(application.applicationContext).addArtist(artistId, artists)
            return artists
        } else {
            Log.d("Cache decision", "return ${potentialResp.size} elements from cache")
            return potentialResp
        }
    }
}
