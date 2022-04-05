package com.example.rickandmorty.base

import com.example.rickandmorty.common.resource.Resource
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.CacheRequest

open class BaseRepository {

    protected fun <T> doRequest(
        request: suspend () -> T,
        writeDataBase: suspend (data: T) -> Unit
    ) = flow {
        emit(Resource.Loading())
        try {
            request()?.let {
                writeDataBase(it)
                emit(Resource.Success(data = it))
            }
        }catch (ioException: Exception){
            emit(
                Resource.Error(
                    data = null , message = ioException.localizedMessage ?: "Error"
                )
            )
        }
    }

    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = request()))
        } catch (e: IOException) {
            emit(
                Resource.Error(null, e.localizedMessage ?: "Error")
            )
        }
    }
}
