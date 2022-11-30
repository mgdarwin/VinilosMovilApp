package com.example.vinilosmovilapp.network

import android.content.Context
import android.util.Log
import com.example.vinilosmovilapp.models.Artist
import com.example.vinilosmovilapp.models.Collector
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
    private var artists: HashMap<String, List<Artist>> = hashMapOf()
    private var artistDetail : HashMap<Int, List<Artist>> = hashMapOf()
    private var collectors: HashMap<String, List<Collector>> = hashMapOf()

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

    fun addAlbum(albumId : Int, album : Album){
        Log.d("cacheManager action","adding album $albumId to cache...")
        if (!albumDetail.containsKey(albumId)){
            Log.d("cacheManager action","Saved Album of ID $albumId to cache")
            albumDetail[albumId] = listOf(album)
        }
    }

    fun getAlbum(albumId: Int) : List<Album> {
        Log.d("cacheManager action","retrieving album $albumId from cache")
        return if (albumDetail.containsKey(albumId)) albumDetail[albumId]!! else listOf()
    }

    fun addArtists(artistsToAdd :List<Artist>){
        Log.d("cacheManager action","adding artists to cache...")
        if (!artists.containsKey("artists")){
            Log.d("cacheManager action","Saved Artists data to cache")
            artists["artists"] = artistsToAdd
        }
    }

    fun getArtists() : List<Artist> {
        Log.d("cacheManager action","retrieving artist from cache")
        return if (artists.containsKey("artists")) artists["artists"]!! else listOf()
    }

    fun addArtist(artistId : Int, artist : List<Artist>){
        Log.d("cacheManager action","adding artist $artistId to cache...")
        if (!artistDetail.containsKey(artistId)){
            Log.d("cacheManager action","Saved Artist of ID $artistId to cache")
            artistDetail[artistId] = artist
        }
    }

    fun getArtist(artistId: Int) : List<Artist> {
        Log.d("cacheManager action","retrieving artist $artistId from cache")
        return if (artistDetail.containsKey(artistId)) artistDetail[artistId]!! else listOf()
    }

    fun addCollectors(collectorsToAdd :List<Collector>){
        Log.d("cacheManager action","adding collectors to cache...")
        if (!collectors.containsKey("collectors")){
            Log.d("cacheManager action","Saved new collectors data to cache")
            collectors["collectors"] = collectorsToAdd
        }
    }

    fun getCollectors() : List<Collector> {
        Log.d("cacheManager action","retrieving collectors data from cache")
        return if (collectors.containsKey("collectors")) collectors["collectors"]!! else listOf()
    }

}