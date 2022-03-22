package com.example.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.common.extensions.setImage
import com.example.rickandmorty.databinding.ItemViewBinding
import com.example.rickandmorty.models.RickAndMortyCharacters

class CharacterAdapter :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    private var list: List<RickAndMortyCharacters> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(
            (ItemViewBinding.inflate
                (
                LayoutInflater.from(parent.context), parent, false
            ))
        )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size

    fun setList(list: List<RickAndMortyCharacters>) {
        this.list = list
        notifyDataSetChanged()
    }

    class CharacterViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RickAndMortyCharacters) {
            binding.imageChar.setImage(data.image)
            binding.nameTv.text = data.name
        }

    }
}