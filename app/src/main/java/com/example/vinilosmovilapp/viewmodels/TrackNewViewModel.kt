package com.example.vinilosmovilapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilosmovilapp.models.Album
import com.example.vinilosmovilapp.models.Track
import com.example.vinilosmovilapp.repositories.AlbumDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class TrackNewViewModel(application: Application) : AndroidViewModel(application) {

    private var _eventNetworkSuccess = MutableLiveData<Boolean>(false)
    val eventNetworkSuccess: LiveData<Boolean>
        get() = _eventNetworkSuccess

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private var _isSuccessShown = MutableLiveData<Boolean>(false)
    val isSuccessShown: LiveData<Boolean>
        get() = _isSuccessShown

    private var _currentAlbum = MutableLiveData<Album?>()
    val currentAlbum: MutableLiveData<Album?>
        get() = _currentAlbum

    private var albumDetailRepository = AlbumDetailRepository(application)

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    fun onSuccessShown() {
        _isSuccessShown.value = true
    }

    fun postTrackToAlbum(album: Album, track: Track) {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    val data = albumDetailRepository.addTrackToAlbum(track, album)
                    _eventNetworkError.postValue(false)
                    _eventNetworkSuccess.postValue(true)
                    _currentAlbum.postValue(album)
                }
            }
        } catch (e: Exception) {
            Log.d("Error", e.toString())
            _eventNetworkError.value = true
            _currentAlbum.value = null
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TrackNewViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TrackNewViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}