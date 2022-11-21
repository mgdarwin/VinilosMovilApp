package com.example.vinilosmovilapp.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.vinilosmovilapp.database.dao.AlbumsDao
import com.example.vinilosmovilapp.database.dao.CollectorsDao
import com.example.vinilosmovilapp.models.Album
import com.example.vinilosmovilapp.models.Collector
import com.example.vinilosmovilapp.network.CacheManager
import com.example.vinilosmovilapp.network.NetworkServiceAdapter

class AlbumListRepository (val application: Application, private val albumsDao: AlbumsDao){
    suspend fun refreshData(): List<Album> {
        var cached = albumsDao.getAlbums()
        return if (cached.isNullOrEmpty()) {
            Log.d("cacheManager Room", "No cache data, Obtaining albums data from network")
            val cm =
                application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE) {
                emptyList()
            } else NetworkServiceAdapter.getInstance(application).getAlbums()
        }
        else {
            Log.d("cacheManager Room", "Albums retrieved from cache successfully")
            cached
        }
    }
}