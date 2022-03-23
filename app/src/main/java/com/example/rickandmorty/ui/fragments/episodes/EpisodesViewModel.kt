package com.example.rickandmorty.ui.fragments.episodes

import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.data.repositories.EpisodesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(val repository: EpisodesRepository) : BaseViewModel() {
    fun fetchEpisodes() = repository.fetchEpisodes()
}