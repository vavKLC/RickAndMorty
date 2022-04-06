package com.example.rickandmorty.ui.fragments.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.data.repositories.CharacterRepository
import com.example.rickandmorty.models.RickAndMortyCharacters
import com.example.rickandmorty.models.RickAndMortyResponse


class CharactersViewModel constructor(
    private val repository: CharacterRepository
) : BaseViewModel() {

    var page = 1
    var isLoading: Boolean = false

    private val _characterState = MutableLiveData<RickAndMortyResponse<RickAndMortyCharacters>>()
    val characterState: LiveData<RickAndMortyResponse<RickAndMortyCharacters>> = _characterState

    private val _characterLocaleState = MutableLiveData<List<RickAndMortyCharacters>>()
    val characterLocaleState: LiveData<List<RickAndMortyCharacters>> = _characterLocaleState

    fun fetchCharacters() {
        isLoading = true
        repository.fetchCharacters(page).collect(_characterState) {
            page++
            isLoading = false
        }
    }


    fun getCharacters() = repository.getCharacters().collect(
        _characterLocaleState

    )

}