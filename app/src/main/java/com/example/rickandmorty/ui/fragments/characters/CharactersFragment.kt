package com.example.rickandmorty.ui.fragments.characters

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.common.resource.Resource
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.ui.adapters.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment(
) : BaseFragment<FragmentCharactersBinding , CharactersViewModel>(
    R.layout.fragment_characters
){
    override val binding by viewBinding(FragmentCharactersBinding :: bind)
    override val viewModel: CharactersViewModel by viewModels()
    private val characterAdapter = CharacterAdapter()

    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = characterAdapter
    }

    override fun setupObserve() {
        subscribeToCharacters()
    }

    private fun subscribeToCharacters() {
        viewModel.fetchCharacters().observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    Log.e("anime" , "loading")
                }
                is Resource.Error -> {
                    Log.e("Anime " , it.message.toString())
                }
                is Resource.Success ->{
                    Log.e("anime" , "Success")

                    it.data?.results?.let {characterAdapter.setList(it) }
                }
            }
        }
    }
}