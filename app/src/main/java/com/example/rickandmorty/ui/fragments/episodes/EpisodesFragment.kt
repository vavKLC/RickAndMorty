package com.example.rickandmorty.ui.fragments.episodes

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.common.resource.Resource
import com.example.rickandmorty.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.ui.adapters.EpisodesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<FragmentEpisodesBinding, EpisodesViewModel>(
    R.layout.fragment_episodes
) {
    override val binding by viewBinding(FragmentEpisodesBinding :: bind)
    override val viewModel : EpisodesViewModel by viewModels()
    private val episodesAdapter = EpisodesAdapter()

    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = episodesAdapter
    }

    override fun setupObserve() {
        subscribeToEpisodes()
    }

    private fun subscribeToEpisodes() {
        viewModel.fetchEpisodes().observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    Log.e("good" , "norm")
                }
                is Resource.Error -> {
                    Log.e("not good" , it.message.toString())
                }
                is Resource.Success -> {
                    it.data?.results?.let { it1 -> episodesAdapter.setList(it1) }
                }
            }
        }
    }
}