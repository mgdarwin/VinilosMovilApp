package com.example.vinilosmovilapp.repositories

import android.app.Application
import android.util.Log
import com.example.vinilosmovilapp.models.Collector
import com.example.vinilosmovilapp.network.CacheManager
import com.example.vinilosmovilapp.network.NetworkServiceAdapter

class CollectorDetailRepository (val application: Application){
    suspend fun refreshData(collectorId: Int) : List<Collector> {
        var potentialResp =
            CacheManager.getInstance(application.applicationContext).getCollector(collectorId)
        if (potentialResp.isEmpty()){
            Log.d("cacheManager action","No cache data, Obtaining album $collectorId from network")
            var collectordetail = NetworkServiceAdapter.getInstance(application).getCollector(collectorId)
            CacheManager.getInstance(application.applicationContext).addCollector(collectorId,collectordetail)
            return collectordetail
        }
        else{
            Log.d("cacheManager action","Album $collectorId retrieved from cache successfully")
            return potentialResp
        }

    }
}