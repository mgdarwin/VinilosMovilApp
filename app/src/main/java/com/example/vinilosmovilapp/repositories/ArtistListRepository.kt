package com.example.vinilosmovilapp.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.vinilosmovilapp.database.dao.AlbumsDao
import com.example.vinilosmovilapp.database.dao.ArtistsDao
import com.example.vinilosmovilapp.models.Album
import com.example.vinilosmovilapp.models.Artist
import com.example.vinilosmovilapp.network.CacheManager
import com.example.vinilosmovilapp.network.NetworkServiceAdapter

class ArtistListRepository (val application: Application, private val artistsDao: ArtistsDao){
    suspend fun refreshData(): List<Artist> {
        var cached = artistsDao.getArtists()
        return if (cached.isNullOrEmpty()) {
            Log.d("cacheManager Room", "No cache data, Obtaining artists data from network")
            val cm =
                application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE) {
                emptyList()
            } else NetworkServiceAdapter.getInstance(application).getArtists()
        }
        else {
            Log.d("cacheManager Room", "Artists retrieved from cache successfully")
            cached
        }
    }
}