package com.example.marveltestapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.marveltestapp.databinding.CharacterItemBinding
import com.example.marveltestapp.domain.Character

class CharacterItemsAdapter : ListAdapter<Character, CharacterItemViewHolder>(CharacterItemDiffCallback()) {

    companion object {
        const val VIEW_TYPE = 0
        const val MAX_POOL_SIZE = 15
    }

    var onCharacterClickListener: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        val binding = CharacterItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        val currentCharacter = getItem(position)
        holder.bind(currentCharacter)
        val binding = holder.binding
        binding.root.setOnClickListener {
            onCharacterClickListener?.invoke(currentCharacter)
        }
    }
}