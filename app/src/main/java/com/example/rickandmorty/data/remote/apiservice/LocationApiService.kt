package com.example.rickandmorty.data.remote.apiservice

import com.example.rickandmorty.models.RickAndMortyLocation
import com.example.rickandmorty.models.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {

    @GET("api/location")
    suspend fun fetchLocations(
        @Query("page")page : Int
    ) : RickAndMortyResponse<RickAndMortyLocation>
}