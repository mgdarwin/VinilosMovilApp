package com.example.vinilosmovilapp.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilosmovilapp.models.Album
import com.example.vinilosmovilapp.network.NetworkServiceAdapter

class AlbumListRepository (val application: Application) {
    fun refreshData(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getAlbums({
            callback(it)
        },
           onError
        )
    }
}