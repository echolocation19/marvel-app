package com.example.marveltestapp.domain

import androidx.lifecycle.LiveData

interface CharactersRepository {
    fun getCharactersList(): LiveData<List<Character>>
    fun getCharacterById(id: Int): LiveData<CharacterInfo>
    fun loadCharactersList(): Unit
    suspend fun loadCharacterById(id: Int)
}