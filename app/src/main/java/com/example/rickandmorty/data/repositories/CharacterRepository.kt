package com.example.rickandmorty.data.repositories

import com.example.rickandmorty.base.BaseRepository
import com.example.rickandmorty.data.local.db.daos.CharacterDao
import com.example.rickandmorty.data.remote.apiservice.CharacterApiService


class CharacterRepository constructor(
    private val service: CharacterApiService,
    private val characterDao: CharacterDao
) : BaseRepository() {

    fun fetchCharacters(page: Int) = doRequest(
        { service.fetchCharacters(page) },
        { characters ->
            characterDao.insertCharacter(*characters.results.toTypedArray())
        }
    )

    fun getCharacters() = doRequest {
        characterDao.getCharacter()
    }

}