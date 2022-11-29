package com.example.vinilosmovilapp.repositories

import android.app.Application
import android.util.Log
import com.example.vinilosmovilapp.models.Album
import com.example.vinilosmovilapp.models.Track
import com.example.vinilosmovilapp.network.CacheManager
import com.example.vinilosmovilapp.network.NetworkServiceAdapter

class AlbumDetailRepository (val application: Application) {
    suspend fun refreshData(albumId: Int) : List<Album> {
        var potentialResp =
            CacheManager.getInstance(application.applicationContext).getAlbum(albumId)
        if (potentialResp.isEmpty()){
            Log.d("cacheManager action","No cache data, Obtaining album $albumId from network")
            var albumdetail = NetworkServiceAdapter.getInstance(application).getAlbum(albumId)
            CacheManager.getInstance(application.applicationContext).addAlbum(albumId,albumdetail)
            return albumdetail
        }
        else{
            Log.d("cacheManager action","Album $albumId retrieved from cache successfully")
            return potentialResp
        }

    }

    suspend fun addTrackToAlbum(track: Track, album:Album) {
        NetworkServiceAdapter.getInstance(application).addTrackToAlbum(track, album)
    }


}