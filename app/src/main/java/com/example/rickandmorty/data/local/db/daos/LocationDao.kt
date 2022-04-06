package com.example.rickandmorty.data.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.models.RickAndMortyLocation

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(vararg users: RickAndMortyLocation)

    @Query("SELECT * FROM rickandmortylocation")
    suspend fun getLocation(): List<RickAndMortyLocation>
}