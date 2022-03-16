package com.example.marveltestapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.marveltestapp.domain.Character

class CharacterItemDiffCallback : ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem.characterId == newItem.characterId

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem == newItem
}
