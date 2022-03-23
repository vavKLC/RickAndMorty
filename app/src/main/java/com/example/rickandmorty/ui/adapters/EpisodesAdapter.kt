package com.example.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemEpisodeBinding
import com.example.rickandmorty.models.RickAndMortyEpisodes

class EpisodesAdapter :
    RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder>() {
    private var list: List<RickAndMortyEpisodes> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder =
        EpisodesViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun setList(list: List<RickAndMortyEpisodes>){
        this.list = list
    }

    override fun getItemCount(): Int = list.size

    class EpisodesViewHolder(val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RickAndMortyEpisodes) {
            binding.tvEpisodeName.text = data.episode
            binding.tvAirDate.text = data.date
            binding.tvEpisodeCode.text = data.code
        }

    }
}