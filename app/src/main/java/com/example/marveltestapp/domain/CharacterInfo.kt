package com.example.marveltestapp.domain

data class CharacterInfo(
    val id: Int,
    val name: String,
    val modified: String,
    val thumbnail: String,
    val comicsUri: List<String>
)