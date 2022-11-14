package com.example.vinilosmovilapp.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilosmovilapp.models.Collector
import com.example.vinilosmovilapp.network.NetworkServiceAdapter

class CollectorListRepository (val application: Application) {
    fun refreshData(callback: (List<Collector>) -> Unit, onError: (VolleyError) -> Unit) {
        NetworkServiceAdapter.getInstance(application).getCollectors(
            {
                callback(it)
            },
            onError
        )
    }
}