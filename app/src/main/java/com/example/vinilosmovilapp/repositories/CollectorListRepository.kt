package com.example.vinilosmovilapp.repositories

import android.app.Application
import com.example.vinilosmovilapp.models.Collector
import com.example.vinilosmovilapp.network.NetworkServiceAdapter

class CollectorListRepository (val application: Application) {
    suspend fun refreshData(): List<Collector> {
        return(NetworkServiceAdapter.getInstance(application).getCollectors())
    }
}