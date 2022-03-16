package com.example.marveltestapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_characters_list")
data class CharacterDbModel(
    val name: String,
    @PrimaryKey(autoGenerate = false)
    val characterId: Int,
    val modified: String
)