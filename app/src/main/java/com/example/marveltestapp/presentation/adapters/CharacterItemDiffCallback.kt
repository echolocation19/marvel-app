package com.example.marveltestapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.marveltestapp.domain.Character

class CharacterItemDiffCallback : ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem == newItem
}
