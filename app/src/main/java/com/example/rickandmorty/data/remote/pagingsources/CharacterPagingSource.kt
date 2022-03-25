package com.example.rickandmorty.data.remote.pagingsources

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.remote.apiservice.CharacterApiService
import com.example.rickandmorty.models.RickAndMortyCharacters
import retrofit2.HttpException

const val CHARACTER_KEY = 1

class CharacterPagingSource(private val service: CharacterApiService) :
    PagingSource<Int, RickAndMortyCharacters>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickAndMortyCharacters> {
        val page = params.key ?: CHARACTER_KEY
        return try {
            val response = service.fetchCharacters(page)
            val nextPageNumber = if(response.info.next == null){
                null
            }else{
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

    override fun getRefreshKey(state: PagingState<Int, RickAndMortyCharacters>): Int? =
            state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
}