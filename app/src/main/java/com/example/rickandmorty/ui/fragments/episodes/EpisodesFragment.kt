package com.example.rickandmorty.ui.fragments.episodes

import android.content.Context
import android.net.ConnectivityManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.common.extensions.submitData
import com.example.rickandmorty.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.ui.adapters.EpisodesAdapter
import com.example.rickandmorty.utils.PaginationScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class EpisodesFragment : BaseFragment<FragmentEpisodesBinding, EpisodesViewModel>(
    R.layout.fragment_episodes
) {
    override val binding by viewBinding(FragmentEpisodesBinding::bind)
    override val viewModel: EpisodesViewModel by viewModel()
    private val episodesAdapter = EpisodesAdapter()


    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() = with(binding.recyclerView) {
        val linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager
        adapter = episodesAdapter

        addOnScrollListener(object : PaginationScrollListener(linearLayoutManager, {
            if (isOnline()) {
                viewModel.fetchEpisodes()
            }
        }) {
            override fun isLoading(): Boolean = viewModel.isLoading
        })
    }

    override fun setupObserve() {
        subscribeToEpisodes()
        subscribeToLocaleEpisodes()
    }

    override fun setupRequests() {
        if (viewModel.episodeState.value == null && isOnline()) viewModel.fetchEpisodes()
        else viewModel.getEpisodes()
    }

    private fun isOnline(): Boolean {
        val cm =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    private fun subscribeToLocaleEpisodes() {
        viewModel.episodeLocaleState.observe(viewLifecycleOwner) {
            episodesAdapter.submitData(it)
        }
    }

    private fun subscribeToEpisodes() {
        viewModel.episodeState.observe(viewLifecycleOwner) {
            episodesAdapter.submitData(it.results)
        }
    }
}