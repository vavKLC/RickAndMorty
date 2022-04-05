package com.example.rickandmorty.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.common.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher


abstract class BaseViewModel : ViewModel() {
    protected fun <T> Flow<Resource<T>>.collect(
        state: MutableLiveData<T>,
        addition: (() -> Unit)? = null
    ) {
        viewModelScope.launch {
            collect {
                when(it){
                    is Resource.Loading -> {
                    }
                    is Resource.Error -> {
                        Log.e("error" , it.message.toString())
                    }
                    is Resource.Success -> {
                        addition?.let { addition() }
                        state.postValue(it.data)
                    }
                }
            }
        }
    }
}
