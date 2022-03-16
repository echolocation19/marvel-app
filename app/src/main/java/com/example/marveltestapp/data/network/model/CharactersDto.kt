package com.example.marveltestapp.data.network.model

import androidx.room.Entity

@Entity(tableName = "all_characters_list")
data class CharactersDto(
    val attributionHTML: String,
    val attributionText: String,
    val code: String,
    val copyright: String,
    val data: DataDto,
    val etag: String,
    val status: String
)