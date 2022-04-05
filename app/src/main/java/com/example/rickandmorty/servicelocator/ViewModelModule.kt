package com.example.rickandmorty.servicelocator

import com.example.rickandmorty.ui.fragments.characters.CharactersViewModel
import com.example.rickandmorty.ui.fragments.episodes.EpisodesViewModel
import com.example.rickandmorty.ui.fragments.location.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharactersViewModel(get()) }
    viewModel { EpisodesViewModel(get()) }
    viewModel { LocationViewModel(get()) }
}