package com.example.marveltestapp.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.marveltestapp.databinding.CharacterItemBinding
import com.example.marveltestapp.domain.Character

class CharacterItemViewHolder(
    private val binding: CharacterItemBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {
        binding.apply {
            tvName.text = character.name
            tvLastUpdate.text = character.modified
        }
    }
}