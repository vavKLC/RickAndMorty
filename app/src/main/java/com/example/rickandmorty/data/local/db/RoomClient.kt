package com.example.rickandmorty.data.local.db

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.data.local.db.daos.CharacterDao
import com.example.rickandmorty.data.local.db.daos.EpisodeDao
import com.example.rickandmorty.data.local.db.daos.LocationDao
import java.security.AccessControlContext

class RoomClient {

    fun provideCreateAppDatabase(context: Context) = Room
        .databaseBuilder(
            context,
            AppDataBase::class.java, "database.rickandmorty.com"
        ).fallbackToDestructiveMigration()
        .build()

    fun provideCharacterDao(appDataBase: AppDataBase): CharacterDao = appDataBase.characterDao()
    fun provideEpisodeDao(appDataBase: AppDataBase): EpisodeDao = appDataBase.episodeDao()
    fun provideLocationDao(appDataBase: AppDataBase): LocationDao = appDataBase.locationDao()
}
