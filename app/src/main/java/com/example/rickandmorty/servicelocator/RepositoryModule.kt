package com.example.rickandmorty.servicelocator

import com.example.rickandmorty.data.repositories.CharacterRepository
import com.example.rickandmorty.data.repositories.EpisodesRepository
import com.example.rickandmorty.data.repositories.LocationRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { CharacterRepository(get(), get()) }
    factory { EpisodesRepository(get(), get()) }
    factory { LocationRepository(get(), get()) }
}