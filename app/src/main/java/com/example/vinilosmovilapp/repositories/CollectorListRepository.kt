package com.example.vinilosmovilapp.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.vinilosmovilapp.database.dao.CollectorsDao
import com.example.vinilosmovilapp.models.Collector
import com.example.vinilosmovilapp.network.CacheManager
import com.example.vinilosmovilapp.network.NetworkServiceAdapter

class CollectorListRepository (val application: Application, private val collectorsDao: CollectorsDao){
    suspend fun refreshData(): List<Collector> {
        var cached = collectorsDao.getCollectors()
        return if (cached.isNullOrEmpty()) {
            Log.d("cacheManager Room", "No cache data, Obtaining collectors data from network")
            val cm =
                application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE) {
                emptyList()
            } else NetworkServiceAdapter.getInstance(application).getCollectors()
        }
        else {
            Log.d("cacheManager Room", "Collectors retrieved from cache successfully")
            cached
        }
    }
}