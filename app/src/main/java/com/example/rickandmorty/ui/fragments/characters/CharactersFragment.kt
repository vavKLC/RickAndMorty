package com.example.rickandmorty.ui.fragments.characters

import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.common.extensions.submitData
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.ui.adapters.CharacterAdapter
import com.example.rickandmorty.utils.PaginationScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment :
    BaseFragment<FragmentCharactersBinding, CharactersViewModel>(
        R.layout.fragment_characters
    ) {
    override val binding by viewBinding(FragmentCharactersBinding::bind)
    override val viewModel: CharactersViewModel by viewModel()
    private val characterAdapter = CharacterAdapter()

    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() = with(binding.recyclerView) {
        val linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager
        adapter = characterAdapter

        addOnScrollListener(object : PaginationScrollListener(linearLayoutManager, {
            if (isOnline()) {
                viewModel.fetchCharacters()
            }
        }) {
            override fun isLoading(): Boolean = viewModel.isLoading
        })
    }

    override fun setupObserve() {
        subscribeToCharacters()
        subscribeToCharactersLocal()
    }

    private fun subscribeToCharactersLocal() {
        viewModel.characterLocaleState.observe(viewLifecycleOwner) {
            characterAdapter.submitData(it)
        }

    }

    override fun setupRequests() {
        if (viewModel.characterState.value == null && isOnline()) viewModel.fetchCharacters()
        else viewModel.getCharacters()
    }

    private fun isOnline(): Boolean {
        val cm = requireActivity().getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    private fun subscribeToCharacters() {
        viewModel.characterState.observe(viewLifecycleOwner) {
            characterAdapter.submitData(it.results)
        }
    }
}