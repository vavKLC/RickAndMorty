package com.example.rickandmorty.data.remote

import com.example.rickandmorty.common.constants.Constants.BASE_URL
import com.example.rickandmorty.data.remote.apiservice.CharacterApiService
import com.example.rickandmorty.data.remote.apiservice.EpisodesApiService
import com.example.rickandmorty.data.remote.apiservice.LocationApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val okHttpClient : OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(provideLoggingInteceptor())
        .connectTimeout(30 , TimeUnit.SECONDS)
        .writeTimeout(30 , TimeUnit.SECONDS)
        .readTimeout(30 , TimeUnit.SECONDS)
        .build()

    private fun provideLoggingInteceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideCharacterApiService() = retrofit.create(CharacterApiService :: class.java)

    fun provideLocationApiService() = retrofit.create(LocationApiService :: class.java)

    fun provideEpisodesApiService() = retrofit.create(EpisodesApiService :: class.java)
}