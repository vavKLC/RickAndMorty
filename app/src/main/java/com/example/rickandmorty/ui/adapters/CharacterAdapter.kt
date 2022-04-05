package com.example.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.common.extensions.setImage
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.models.RickAndMortyCharacters

class CharacterAdapter :
    ListAdapter<RickAndMortyCharacters, CharacterAdapter.CharacterViewHolder>(
        CharacterComparator
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }


    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RickAndMortyCharacters) {
            binding.imageChar.setImage(data.image)
            binding.nameTv.text = data.name


        }
    }
}

object CharacterComparator : DiffUtil.ItemCallback<RickAndMortyCharacters>() {
    override fun areItemsTheSame(
        oldItem: RickAndMortyCharacters,
        newItem: RickAndMortyCharacters
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: RickAndMortyCharacters,
        newItem: RickAndMortyCharacters
    ): Boolean =
        oldItem == newItem

}