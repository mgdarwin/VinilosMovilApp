package com.example.vinilosmovilapp.repositories

import android.app.Application
import android.util.Log
import com.example.vinilosmovilapp.network.CacheManager
import com.example.vinilosmovilapp.network.NetworkServiceAdapter
import com.example.vinilosmovilapp.models.Album

class AlbumListRepository(val application: Application) {

    suspend fun refreshData(): List<Album> {
        var potentialResp =
            CacheManager.getInstance(application.applicationContext).getAlbums()
        if (potentialResp.isEmpty()) {
            Log.d("cacheManager action", "No cache data, Obtaining albums from network")
            var albums = NetworkServiceAdapter.getInstance(application).getAlbums()
            CacheManager.getInstance(application.applicationContext).addAlbums(albums)
            return albums
        } else {
            Log.d("cacheManager action", "Albums retrieved from cache successfully")
            return potentialResp
        }

    }
}