package com.example.rickandmorty.data.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.models.RickAndMortyEpisodes

@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(vararg users: RickAndMortyEpisodes)

    @Query("SELECT * FROM rickandmortyepisodes")
    suspend fun getEpisode(): List<RickAndMortyEpisodes>
}