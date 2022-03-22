package com.example.rickandmorty.models

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class RickAndMortyResponse<T>(

    @SerializedName("info")
    val info : Info,

    @SerializedName("result")
    val result: ArrayList<T>
)
