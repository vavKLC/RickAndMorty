package com.example.rickandmorty.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmorty.data.remote.apiservice.CharacterApiService
import com.example.rickandmorty.data.remote.pagingsources.CharacterPagingSource
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val service: CharacterApiService) {

    fun fetchCharacters() = Pager(
        PagingConfig(pageSize = 20)
    ) {
        CharacterPagingSource(service)
    }.flow

}