package com.example.vinilosmovilapp.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilosmovilapp.models.Artist
import com.example.vinilosmovilapp.network.NetworkServiceAdapter
import com.example.vinilosmovilapp.repositories.ArtistDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistDetailViewModel(application: Application, artistId: Int) :  AndroidViewModel(application)  {

    private val artistDetailRepository = ArtistDetailRepository(application)
    private val _artists = MutableLiveData<List<Artist>>()

    val artists: LiveData<List<Artist>>
        get() = _artists

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id : Int = artistId

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var data = artistDetailRepository.refreshData(id)
                    _artists.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val artistId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArtistDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArtistDetailViewModel(app, artistId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}