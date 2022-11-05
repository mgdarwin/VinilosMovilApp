package com.example.vinilosmovilapp.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilosmovilapp.models.Album
import com.example.vinilosmovilapp.network.NetworkServiceAdapter

class AlbumDetailRepository (val application: Application) {
    fun refreshData(albumId: Int, callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getAlbum(albumId, {
            callback(it)
        },
            onError
        )
    }
}