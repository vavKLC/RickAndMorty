package com.example.rickandmorty.data.remote.pagingsources

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.remote.apiservice.LocationApiService
import com.example.rickandmorty.models.RickAndMortyLocation
import retrofit2.HttpException

const val LOCATION_KEY = 1

class LocationPagingSource(private val service: LocationApiService) :
    PagingSource<Int, RickAndMortyLocation>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickAndMortyLocation> {
        val page = params.key ?: LOCATION_KEY
        return try {
            val response = service.fetchLocations(page)
            val nextPageNumber = if (response.info.next == null) {
                null
            } else {
                Uri.parse(response.info.next).getQueryParameter("page")!!.toInt()
            }
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: java.io.IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RickAndMortyLocation>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }