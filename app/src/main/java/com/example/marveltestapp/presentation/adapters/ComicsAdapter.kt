package com.example.marveltestapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marveltestapp.databinding.ComicsItemBinding

class ComicsAdapter(
    private val modelList: List<Comics>
) :
    RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

    companion object {
        const val VIEW_TYPE = 0
        const val MAX_POOL_SIZE = 10
    }

    class ComicsViewHolder(val binding: ComicsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val binding = ComicsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ComicsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val currentComics = modelList[position]
        with(holder) {
            binding.tvComicsName.text = currentComics.content
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }
}