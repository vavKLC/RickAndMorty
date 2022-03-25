package com.example.rickandmorty.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmorty.data.remote.apiservice.LocationApiService
import com.example.rickandmorty.data.remote.pagingsources.LocationPagingSource
import javax.inject.Inject

class LocationRepository @Inject constructor(private val service: LocationApiService) {

    fun fetchLocations() = Pager(
        PagingConfig(pageSize = 20)
    ) {
        LocationPagingSource(service)
    }.flow
}