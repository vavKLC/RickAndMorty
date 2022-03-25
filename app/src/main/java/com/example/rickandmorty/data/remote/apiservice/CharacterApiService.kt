package com.example.rickandmorty.data.remote.apiservice

import com.example.rickandmorty.models.RickAndMortyCharacters
import com.example.rickandmorty.models.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiService {

    @GET("api/character")
    suspend fun fetchCharacters(
        @Query("page") page : Int
    ): RickAndMortyResponse<RickAndMortyCharacters>
}