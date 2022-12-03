package com.example.vinilosmovilapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilosmovilapp.models.Album
import com.example.vinilosmovilapp.repositories.AlbumAddRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class AlbumAddViewModel(application: Application): AndroidViewModel(application) {
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

    private var albumAddRepository = AlbumAddRepository(application)

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    fun onSuccessShown() {
        _isSuccessShown.value = true
    }

    fun postAlbum(album: Album) {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    val data = albumAddRepository.addAlbum(album)
                    _eventNetworkError.postValue(false)
                    _eventNetworkSuccess.postValue(true)
                }
            }
        } catch (e: Exception) {
            Log.d("Error", e.toString())
            _eventNetworkError.value = true
        }
    }

    class Factory(val app: Application): ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>) : T {
            if (modelClass.isAssignableFrom(AlbumAddViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumAddViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}

