package com.example.rickandmorty.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty.data.local.db.daos.CharacterDao
import com.example.rickandmorty.data.local.db.daos.EpisodeDao
import com.example.rickandmorty.data.local.db.daos.LocationDao
import com.example.rickandmorty.models.RickAndMortyCharacters
import com.example.rickandmorty.models.RickAndMortyEpisodes
import com.example.rickandmorty.models.RickAndMortyLocation

@Database(
    entities = [RickAndMortyCharacters::class
        , RickAndMortyEpisodes::class
        , RickAndMortyLocation::class],
    version = 2
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun locationDao(): LocationDao
}