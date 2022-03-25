package com.example.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemEpisodeBinding
import com.example.rickandmorty.models.RickAndMortyCharacters
import com.example.rickandmorty.models.RickAndMortyEpisodes

class EpisodesAdapter :
    PagingDataAdapter< RickAndMortyEpisodes,EpisodesAdapter.EpisodesViewHolder>(
        EpisodeComparator
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder =
        EpisodesViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }


    class EpisodesViewHolder(val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RickAndMortyEpisodes) {
            binding.tvEpisodeName.text = data.name
            binding.tvAirDate.text = data.air_date
            binding.tvEpisodeCode.text = data.episode_code
        }

    }
}
object EpisodeComparator : DiffUtil.ItemCallback<RickAndMortyEpisodes>() {
    override fun areItemsTheSame(
        oldItem: RickAndMortyEpisodes,
        newItem: RickAndMortyEpisodes
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: RickAndMortyEpisodes,
        newItem: RickAndMortyEpisodes
    ): Boolean =
        oldItem == newItem

}