package com.example.vinilosmovilapp.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilosmovilapp.models.Album
import com.example.vinilosmovilapp.models.Artist
import com.example.vinilosmovilapp.models.Collector
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkServiceAdapter constructor(context : Context ) {

    companion object {
        //const val BASE_URL= "https://back-vinyls-populated.herokuapp.com/"
        //const val BASE_URL = "https://vynils-back-heroku.herokuapp.com/"
        const val BASE_URL = "https://vynils-back-uniandes.herokuapp.com/"  //Equipo
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    suspend fun getAlbums() = suspendCoroutine<List<Album>> { cont->
        requestQueue.add(
            getRequest("albums",
                { response ->
                    val resp = JSONArray(response)
                    val list = mutableListOf<Album>()
                    for (i in 0 until resp.length()) {
                        val item = resp.getJSONObject(i)
                        list.add(
                            i,
                            Album(
                                albumId = item.getInt("id"),
                                name = item.getString("name"),
                                cover = item.getString("cover"),
                                recordLabel = item.getString("recordLabel"),
                                releaseDate = item.getString("releaseDate"),
                                genre = item.getString("genre"),
                                description = item.getString("description")
                            )
                        )
                    }
                    cont.resume(list)
                },
                {
                    cont.resumeWithException(it)
                }
            )
        )
    }

    suspend fun getAlbum(albumId:Int) = suspendCoroutine<List<Album>> { cont->
        requestQueue.add(
            requestQueue.add(getRequest("albums/$albumId",
                { response ->
                    val resp = JSONObject(response)
                    val list = mutableListOf<Album>()
                    list.add(
                        0,
                        Album(
                            albumId = resp.getInt("id"),
                            name = resp.getString("name"),
                            cover = resp.getString("cover"),
                            recordLabel = resp.getString("recordLabel"),
                            releaseDate = resp.getString("releaseDate"),
                            genre = resp.getString("genre"),
                            description = resp.getString("description")
                        )
                    )
                    cont.resume(list)
                },
                {
                    cont.resumeWithException(it)
                }
            )
            )
        )
    }

    suspend fun getArtists() = suspendCoroutine<List<Artist>> { cont->
        requestQueue.add(getRequest("musicians",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Artist>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Artist(
                        artistId = item.getInt("id"),
                        name = item.getString("name"),
                        image = item.getString("image"),
                        description = item.getString("description"),
                        birthDate = item.getString("birthDate")))
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getArtist(artistId:Int) = suspendCoroutine<List<Artist>> { cont ->
        requestQueue.add(getRequest("musicians/$artistId",
            { response ->
                val resp = JSONObject(response)
                println("********* Respuesta a la peticion de un artista GET ********")
                println(resp)
                val list = mutableListOf<Artist>()
                list.add(0, Artist(
                    artistId = resp.getInt("id"),
                    name = resp.getString("name"),
                    image = resp.getString(
                        "image"),
                    description = resp.getString("description"),
                    birthDate = resp.getString("birthDate")))
                println("**************** esta es la lista de salida de la peticion GET Artist **************")
                println(list)
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getCollectors() = suspendCoroutine<List<Collector>> { cont->
        requestQueue.add(
            getRequest("collectors",
                { response ->
                    val resp = JSONArray(response)
                    val list = mutableListOf<Collector>()
                    for (i in 0 until resp.length()) {
                        val item = resp.getJSONObject(i)
                        list.add(
                            i,
                            Collector(
                                collectorId = item.getInt("id"),
                                name = item.getString("name"),
                                telephone = item.getString("telephone"),
                                email = item.getString("email"),
                                comments = item.getJSONArray("comments"),
                                favoritePerformers = item.getJSONArray("favoritePerformers"),
                                collectorAlbums = item.getJSONArray("collectorAlbums")
                            )
                        )
                    }
                    cont.resume(list)
                 },
                {
                    cont.resumeWithException(it)
                }
            )
        )
    }

    suspend fun getCollector(collectorId:Int) = suspendCoroutine<List<Collector>> { cont ->
        requestQueue.add(getRequest("collectors/$collectorId",
            { response ->
                val resp = JSONObject(response)
                println("********* Respuesta a la peticion de un artista GET ********")
                println(resp)
                val list = mutableListOf<Collector>()
                list.add(0, Collector(
                    collectorId = resp.getInt("id"),
                    name = resp.getString("name"),
                    telephone = resp.getString("telephone"),
                    email = resp.getString("email"),
                    comments = resp.getJSONArray("comments"),
                    favoritePerformers = resp.getJSONArray("favoritePerformers"),
                    collectorAlbums = resp.getJSONArray("collectorAlbums")))
                println("**************** esta es la lista de salida de la peticion GET Artist **************")
                println(list)
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }))
    }

    private fun getRequest(
        path: String,
        responseListener: Response.Listener<String>,
        errorListener: Response.ErrorListener
    ): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener)
    }

    private fun postRequest(
        path: String,
        body: JSONObject,
        responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ): JsonObjectRequest {
        return JsonObjectRequest(
            Request.Method.POST,
            BASE_URL + path,
            body,
            responseListener,
            errorListener
        )
    }

    private fun putRequest(
        path: String,
        body: JSONObject,
        responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ): JsonObjectRequest {
        return JsonObjectRequest(
            Request.Method.PUT,
            BASE_URL + path,
            body,
            responseListener,
            errorListener
        )
    }
}
