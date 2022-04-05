package com.example.rickandmorty.ui.fragments.location


import android.content.Context
import android.net.ConnectivityManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.common.extensions.submitData
import com.example.rickandmorty.databinding.FragmentLocationBinding
import com.example.rickandmorty.ui.adapters.LocationAdapter
import com.example.rickandmorty.utils.PaginationScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel



class LocationFragment
    : BaseFragment<FragmentLocationBinding, LocationViewModel>(
    R.layout.fragment_location
) {
    override val binding by viewBinding(FragmentLocationBinding::bind)
    override val viewModel: LocationViewModel by viewModel()
    private val locationAdapter = LocationAdapter()

    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() = with(binding.recyclerView) {
        val linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager
        adapter = locationAdapter


        addOnScrollListener(object : PaginationScrollListener(linearLayoutManager, {
            viewModel.fetchLocations()
        }) {
            override fun isLoading(): Boolean = viewModel.isLoading
        })
    }

    override fun setupRequests() {
        if (viewModel.locationState.value == null && isOnline()) viewModel.fetchLocations()
        else    viewModel.getLocations()
    }

    private fun isOnline(): Boolean {
        val cm = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    override fun setupObserve() {
        subscribeToLocation()
        subscribeToLocaleLocation()
    }

    private fun subscribeToLocaleLocation() {
        viewModel.locationLocaleState.observe(viewLifecycleOwner){
            locationAdapter.submitData(it)
        }
    }

    private fun subscribeToLocation() {
        viewModel.locationState.observe(viewLifecycleOwner) {
            locationAdapter.submitData(it.results)
        }
    }
}