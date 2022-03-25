package com.example.rickandmorty.models

import com.google.gson.annotations.SerializedName

data class RickAndMortyEpisodes(

    @SerializedName("id")
    val id : Int ,

    @SerializedName("name")
    val name : String,

    @SerializedName("air_date")
    val air_date : String,

    @SerializedName("episode")
    val episode_code : String
)
