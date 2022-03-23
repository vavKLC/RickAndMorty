package com.example.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.databinding.ItemLocationBinding
import com.example.rickandmorty.models.RickAndMortyLocation

class LocationAdapter :
    RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {
    private var list : List<RickAndMortyLocation> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder =
        LocationViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun setList(list : List<RickAndMortyLocation>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    class LocationViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RickAndMortyLocation) {
            binding.locationNameTv.text = data.name
            binding.locationNameTypeTv.text = data.type
        }

    }

}

