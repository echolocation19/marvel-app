package com.example.marveltestapp.presentation.adapters

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marveltestapp.R
import com.example.marveltestapp.databinding.CharacterItemBinding
import com.example.marveltestapp.domain.Character
import com.squareup.picasso.Picasso

class CharacterItemViewHolder(
    val context: Context,
    val binding: CharacterItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {
        binding.apply {
            tvName.text = character.name
            val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
            tvLastUpdate.text = String.format(lastUpdateTemplate, character.modified)
            Picasso.get().load(character.thumbnail)
                .resize(IMAGE_WIDTH, IMAGE_HEIGHT)
                .into(ivThumbnail)
        }
    }

    companion object {
        private const val IMAGE_WIDTH = 100
        private const val IMAGE_HEIGHT = 100
    }

}