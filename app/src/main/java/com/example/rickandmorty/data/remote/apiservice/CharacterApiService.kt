package com.example.rickandmorty.data.remote.apiservice

import com.example.rickandmorty.models.RickAndMortyCharacters
import com.example.rickandmorty.models.RickAndMortyResponse
import retrofit2.http.GET

interface CharacterApiService {

    @GET("api/character")
    suspend fun fetchCharacters() : RickAndMortyResponse<RickAndMortyCharacters>
}