package com.example.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemLocationBinding
import com.example.rickandmorty.models.RickAndMortyLocation

class LocationAdapter :
    ListAdapter<RickAndMortyLocation,LocationAdapter.LocationViewHolder>(
        LocationComparator
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder =
        LocationViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }



    class LocationViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RickAndMortyLocation) {
            binding.locationNameTv.text = data.name
            binding.locationNameTypeTv.text = data.type
        }

    }

}
object LocationComparator : DiffUtil.ItemCallback<RickAndMortyLocation>() {
    override fun areItemsTheSame(
        oldItem: RickAndMortyLocation,
        newItem: RickAndMortyLocation
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: RickAndMortyLocation,
        newItem: RickAndMortyLocation
    ): Boolean =
        oldItem == newItem

}

