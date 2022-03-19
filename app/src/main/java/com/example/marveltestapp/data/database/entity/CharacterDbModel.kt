package com.example.marveltestapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_characters_list")
data class CharacterDbModel(
    val name: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val modified: String,
    val thumbnail: String,
    val comicsUri: List<String>
)