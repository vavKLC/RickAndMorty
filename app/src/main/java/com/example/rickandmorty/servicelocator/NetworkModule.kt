package com.example.rickandmorty.servicelocator

import com.example.rickandmorty.data.remote.RetrofitClient
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitClient() }
    single { get<RetrofitClient>().provideCharacterApiService() }
    single { get<RetrofitClient>().provideEpisodesApiService() }
    single { get<RetrofitClient>().provideLocationApiService() }
}

