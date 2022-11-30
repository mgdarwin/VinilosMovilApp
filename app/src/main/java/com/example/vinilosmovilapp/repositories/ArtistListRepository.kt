package com.example.vinilosmovilapp.repositories

import android.app.Application
import android.util.Log
import com.example.vinilosmovilapp.models.Artist
import com.example.vinilosmovilapp.network.CacheManager
import com.example.vinilosmovilapp.network.NetworkServiceAdapter

class ArtistListRepository(val application: Application) {

    suspend fun refreshData(): List<Artist> {
        var potentialResp =
            CacheManager.getInstance(application.applicationContext).getArtists()
        if (potentialResp.isEmpty()) {
            Log.d("cacheManager action", "No cache data, Obtaining artists from network")
            var artists = NetworkServiceAdapter.getInstance(application).getArtists()
            CacheManager.getInstance(application.applicationContext).addArtists(artists)
            return artists
        } else {
            Log.d("cacheManager action", "Artists retrieved from cache successfully")
            return potentialResp
        }

    }
}