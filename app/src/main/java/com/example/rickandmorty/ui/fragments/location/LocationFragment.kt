package com.example.rickandmorty.ui.fragments.location


import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.common.resource.Resource
import com.example.rickandmorty.databinding.FragmentLocationBinding
import com.example.rickandmorty.ui.adapters.CharacterAdapter
import com.example.rickandmorty.ui.adapters.LocationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment(
) : BaseFragment<FragmentLocationBinding , LocationViewModel>(
    R.layout.fragment_location
){
    override val binding by viewBinding(FragmentLocationBinding :: bind)
    override val viewModel : LocationViewModel by viewModels()
    private val locationAdapter = LocationAdapter()

    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = locationAdapter
    }

    override fun setupObserve() {
        subscribeToLocation()
    }

    private fun subscribeToLocation() {
        viewModel.fetchLocations().observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    Log.e("good" , "gruzit")
                }
                is Resource.Error -> {
                    Log.e("not good" , it.message.toString())
                }
                is Resource.Success -> {
                    it.data?.results?.let { it1 -> locationAdapter.setList(it1) }
                }
            }
        }
    }
}