package com.example.rickandmorty.data.repositories

import androidx.lifecycle.liveData
import com.example.rickandmorty.data.remote.apiservice.LocationApiService
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

class LocationRepository @Inject constructor(private val service: LocationApiService) {

    fun fetchLocations() = liveData(Dispatchers.IO) {
        emit(com.example.rickandmorty.common.resource.Resource.Loading())
        try {
            emit(com.example.rickandmorty.common.resource.Resource.Success(service.fetchLocations()))
        } catch (ioException: Exception) {
            emit(
                com.example.rickandmorty.common.resource.Resource.Error(
                    ioException.localizedMessage, null
                )
            )
        }
    }
}