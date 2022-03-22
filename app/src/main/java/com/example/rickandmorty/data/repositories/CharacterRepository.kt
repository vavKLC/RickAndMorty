package com.example.rickandmorty.data.repositories

import androidx.lifecycle.liveData
import com.example.rickandmorty.common.resource.Resource
import com.example.rickandmorty.data.remote.apiservice.CharacterApiService
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val service: CharacterApiService) {

    fun fetchCharacters() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(service.fetchCharacters()))
        } catch (ioException : Exception){
            emit(Resource.Error(ioException.localizedMessage , null))
        }
    }
}