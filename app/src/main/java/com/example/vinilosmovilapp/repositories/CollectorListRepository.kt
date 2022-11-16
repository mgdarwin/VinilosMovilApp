package com.example.vinilosmovilapp.repositories

import android.app.Application
import android.util.Log
import com.example.vinilosmovilapp.models.Collector
import com.example.vinilosmovilapp.network.CacheManager
import com.example.vinilosmovilapp.network.NetworkServiceAdapter

class CollectorListRepository (val application: Application) {
    suspend fun refreshData(): List<Collector> {
        var potentialResp =
            CacheManager.getInstance(application.applicationContext).getCollectors()
        if (potentialResp.isEmpty()){
            Log.d("cacheManager action","No cache data, Obtaining collectors data from network")
            var collectors = NetworkServiceAdapter.getInstance(application).getCollectors()
            CacheManager.getInstance(application.applicationContext).addCollectors(collectors)
            return collectors
        }
        else{
            Log.d("cacheManager action","Collectors retrieved from cache successfully")
            return potentialResp
        }
    }

}