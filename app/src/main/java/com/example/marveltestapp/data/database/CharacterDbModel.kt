package com.example.marveltestapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_characters_list")
data class CharacterDbModel(
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val characterId: Int,
)