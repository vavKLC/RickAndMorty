package com.example.rickandmorty.ui.fragments.location

import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.data.repositories.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val repository: LocationRepository) :
    BaseViewModel() {
        fun fetchLocations() = repository.fetchLocations()
}