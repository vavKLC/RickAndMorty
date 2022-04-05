package com.example.rickandmorty.data.repositories

import com.example.rickandmorty.base.BaseRepository
import com.example.rickandmorty.data.local.db.daos.LocationDao
import com.example.rickandmorty.data.remote.apiservice.LocationApiService

class LocationRepository constructor(
    private val service: LocationApiService,
    private val locationDao: LocationDao
    ): BaseRepository() {

    fun fetchLocations(page: Int) = doRequest(
        { service.fetchLocations(page) },
        { location ->
            locationDao.insertLocation(*location.results.toTypedArray())
        }
    )

    fun getLocations() = doRequest {
        locationDao.getLocation()
    }
}