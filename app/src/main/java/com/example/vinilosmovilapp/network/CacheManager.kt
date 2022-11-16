package com.example.vinilosmovilapp.network

import android.content.Context
import android.util.Log
import com.example.vinilosmovilapp.models.Album

class CacheManager (context: Context){
    companion object{
        var instance: CacheManager? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CacheManager(context).also {
                    instance = it
                }
            }
    }

    private var albums: HashMap<String, List<Album>> = hashMapOf()
    private var albumDetail : HashMap<Int, List<Album>> = hashMapOf()

    fun addAlbums(albumsToAdd :List<Album>){
        Log.d("cacheManager action","adding albums to cache...")
        if (!albums.containsKey("albums")){
            Log.d("cacheManager action","Saved Albums data to cache")
            albums["albums"] = albumsToAdd
        }
    }

    fun getAlbums() : List<Album> {
        Log.d("cacheManager action","retrieving albums from cache")
        return if (albums.containsKey("albums")) albums["albums"]!! else listOf()
    }

    fun addAlbum(albumId : Int, album : List<Album>){
        Log.d("cacheManager action","adding album $albumId to cache...")
        if (!albumDetail.containsKey(albumId)){
            Log.d("cacheManager action","Saved Album of ID $albumId to cache")
            albumDetail[albumId] = album
        }
    }

    fun getAlbum(albumId: Int) : List<Album> {
        Log.d("cacheManager action","retrieving album $albumId from cache")
        return if (albumDetail.containsKey(albumId)) albumDetail[albumId]!! else listOf()
    }


}