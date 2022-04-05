package com.example.rickandmorty.ui.fragments.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.data.repositories.LocationRepository
import com.example.rickandmorty.models.RickAndMortyLocation
import com.example.rickandmorty.models.RickAndMortyResponse


class LocationViewModel  constructor(private val repository: LocationRepository) :
    BaseViewModel() {
    var page = 1
    var isLoading : Boolean = false

    private val _locationState = MutableLiveData<RickAndMortyResponse<RickAndMortyLocation>>()
    val locationState: LiveData<RickAndMortyResponse<RickAndMortyLocation>> = _locationState

    private val _locationLocaleState = MutableLiveData<List<RickAndMortyLocation>>()
    val locationLocaleState: LiveData<List<RickAndMortyLocation>> = _locationLocaleState

    fun fetchLocations() {
        isLoading = true
        repository.fetchLocations(page).collect(_locationState){
            page++
            isLoading = false
        }
    }


    fun getLocations() = repository.getLocations().collect(
        _locationLocaleState

    )
}