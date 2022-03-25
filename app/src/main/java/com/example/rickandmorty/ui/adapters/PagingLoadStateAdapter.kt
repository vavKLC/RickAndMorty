package com.example.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.LoadingStateBinding

class PagingLoadStateAdapter :
    LoadStateAdapter<PagingLoadStateAdapter.ProgressItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ProgressItemViewHolder =
        ProgressItemViewHolder(
            LoadingStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ProgressItemViewHolder, loadState: LoadState) {
        holder.onBind(loadState)
    }

    class ProgressItemViewHolder(private val binding: LoadingStateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(load: LoadState) {
            binding.progressBar.isVisible = load is LoadState.Loading
        }

    }
}