package com.example.rickandmorty.data.repositories


import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmorty.data.remote.apiservice.EpisodesApiService
import com.example.rickandmorty.data.remote.pagingsources.EpisodePagingSource
import javax.inject.Inject

class EpisodesRepository @Inject constructor(private val service: EpisodesApiService) {

    fun fetchEpisodes() = Pager(
        PagingConfig(pageSize = 20)
    ) {
        EpisodePagingSource(service)
    }.flow
}