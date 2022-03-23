package com.example.rickandmorty.data.repositories

import androidx.lifecycle.liveData
import com.example.rickandmorty.common.resource.Resource
import com.example.rickandmorty.data.remote.apiservice.EpisodesApiService
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import java.lang.Exception
import javax.inject.Inject

class EpisodesRepository @Inject constructor(val service: EpisodesApiService) {

    fun fetchEpisodes() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(service.fetchEpisodes()))
        } catch (ioException: Exception){
            emit(Resource.Error(ioException.localizedMessage , null))
        }
    }
}