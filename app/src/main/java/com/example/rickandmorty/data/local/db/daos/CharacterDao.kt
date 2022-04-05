package com.example.rickandmorty.data.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.models.RickAndMortyCharacters

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(vararg users : RickAndMortyCharacters)

    @Query("SELECT * FROM rickandmortycharacters")
    suspend fun getCharacter() : List<RickAndMortyCharacters>
}