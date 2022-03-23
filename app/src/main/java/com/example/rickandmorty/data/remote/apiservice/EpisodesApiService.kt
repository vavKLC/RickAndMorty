package com.example.rickandmorty.data.remote.apiservice

import com.example.rickandmorty.models.RickAndMortyEpisodes
import com.example.rickandmorty.models.RickAndMortyResponse
import retrofit2.http.GET

interface EpisodesApiService {

    @GET("api/episode")
    suspend fun fetchEpisodes() : RickAndMortyResponse<RickAndMortyEpisodes>
}
