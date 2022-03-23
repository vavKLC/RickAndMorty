package com.example.rickandmorty.ui.fragments.characters

import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepository)
    : BaseViewModel() {

        fun fetchCharacters() = repository.fetchCharacters()

}