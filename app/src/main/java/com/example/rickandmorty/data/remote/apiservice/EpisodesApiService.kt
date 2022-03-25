package com.example.rickandmorty.data.remote.apiservice

import com.example.rickandmorty.models.RickAndMortyEpisodes
import com.example.rickandmorty.models.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodesApiService {

    @GET("api/episodes")
    suspend fun fetchEpisodes(
        @Query("page") page : Int
    ) : RickAndMortyResponse<RickAndMortyEpisodes>
}
