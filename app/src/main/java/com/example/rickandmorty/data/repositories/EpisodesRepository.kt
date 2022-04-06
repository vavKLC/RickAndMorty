package com.example.rickandmorty.data.repositories


import com.example.rickandmorty.base.BaseRepository
import com.example.rickandmorty.data.local.db.daos.EpisodeDao
import com.example.rickandmorty.data.remote.apiservice.EpisodesApiService

class EpisodesRepository constructor(
    private val service: EpisodesApiService,
    private val episodeDao: EpisodeDao
) : BaseRepository() {

    fun fetchEpisodes(page: Int) = doRequest(
        { service.fetchEpisodes(page) },
        { episode ->
            episodeDao.insertEpisode(*episode.results.toTypedArray())
        }
    )

    fun getCharacters() = doRequest {
        episodeDao.getEpisode()
    }
}