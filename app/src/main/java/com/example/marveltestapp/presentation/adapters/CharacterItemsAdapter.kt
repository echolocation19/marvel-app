package com.example.marveltestapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import com.example.marveltestapp.R
import com.example.marveltestapp.databinding.CharacterItemBinding
import com.example.marveltestapp.domain.Character

class CharacterItemsAdapter(private val context: Context) :
    ListAdapter<Character, CharacterItemViewHolder>(CharacterItemDiffCallback()) {

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
        return CharacterItemViewHolder(context, binding)
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        val currentCharacter = getItem(position)
        holder.bind(currentCharacter)
        val binding = holder.binding
        val color = if (position % 2 == 0)
            ContextCompat.getColor(context, R.color.light_gray)
        else ContextCompat.getColor(context, R.color.white)
        binding.root.setCardBackgroundColor(color)
        binding.root.setOnClickListener {
            onCharacterClickListener?.invoke(currentCharacter)
        }
    }
}