package com.example.rickandmorty.ui.fragments.episodes

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.common.resource.Resource
import com.example.rickandmorty.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.ui.adapters.EpisodesAdapter
import com.example.rickandmorty.ui.adapters.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<FragmentEpisodesBinding, EpisodesViewModel>(
    R.layout.fragment_episodes
) {
    override val binding by viewBinding(FragmentEpisodesBinding::bind)
    override val viewModel: EpisodesViewModel by viewModels()
    private val episodesAdapter = EpisodesAdapter()


    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = episodesAdapter
        binding.recyclerView.adapter = episodesAdapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter(),
            footer = PagingLoadStateAdapter()
        )
    }

    override fun setupObserve() {
        subscribeToEpisodes()
    }

    private fun subscribeToEpisodes() {
        lifecycleScope.launch {
            viewModel.fetchEpisodes().collectLatest {
                episodesAdapter.submitData(it)
            }
        }
    }
}