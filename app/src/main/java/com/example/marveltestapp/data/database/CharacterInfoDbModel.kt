package com.example.marveltestapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_info")
data class CharacterInfoDbModel(
    val name: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val modified: String,
    val thumbnail: String
)