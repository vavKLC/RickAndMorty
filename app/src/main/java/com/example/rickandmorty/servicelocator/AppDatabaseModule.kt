package com.example.rickandmorty.servicelocator

import com.example.rickandmorty.data.local.db.RoomClient
import org.koin.dsl.module

val appDataBaseModule = module {
    single { RoomClient() }
    single { get <RoomClient>().provideCreateAppDatabase(get()) }
    single { get<RoomClient>().provideCharacterDao(get())}
    single { get<RoomClient>().provideLocationDao(get()) }
    single { get<RoomClient>().provideEpisodeDao(get()) }
}