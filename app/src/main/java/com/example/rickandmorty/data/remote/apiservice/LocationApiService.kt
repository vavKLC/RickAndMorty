package com.example.rickandmorty.data.remote.apiservice

import com.example.rickandmorty.models.RickAndMortyLocation
import com.example.rickandmorty.models.RickAndMortyResponse
import retrofit2.http.GET

interface LocationApiService {

    @GET("api/location")
    suspend fun fetchLocations() : RickAndMortyResponse<RickAndMortyLocation>
}