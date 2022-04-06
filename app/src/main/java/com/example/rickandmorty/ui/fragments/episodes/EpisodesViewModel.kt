package com.example.rickandmorty.ui.fragments.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.data.repositories.EpisodesRepository
import com.example.rickandmorty.models.RickAndMortyEpisodes
import com.example.rickandmorty.models.RickAndMortyResponse

class EpisodesViewModel constructor(
    val repository: EpisodesRepository
) : BaseViewModel() {

    var page = 1
    var isLoading: Boolean = false

    private val _episodeState = MutableLiveData<RickAndMortyResponse<RickAndMortyEpisodes>>()
    val episodeState: LiveData<RickAndMortyResponse<RickAndMortyEpisodes>> = _episodeState

    private val _episodeLocaleState = MutableLiveData<List<RickAndMortyEpisodes>>()
    val episodeLocaleState: LiveData<List<RickAndMortyEpisodes>> = _episodeLocaleState

    fun fetchEpisodes() {
        isLoading = true
        repository.fetchEpisodes(page).collect(_episodeState) {
            page++
            isLoading = false
        }
    }


    fun getEpisodes() = repository.getCharacters().collect(
        _episodeLocaleState

    )

}