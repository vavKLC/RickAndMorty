package com.example.rickandmorty.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class RickAndMortyLocation(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id : Int ,
    @SerializedName("name")
    val name : String ,

    @SerializedName("type")
    val type : String
)
