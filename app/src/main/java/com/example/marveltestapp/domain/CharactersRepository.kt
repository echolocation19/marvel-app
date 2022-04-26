package com.example.marveltestapp.domain

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope

interface CharactersRepository {
    fun getCharactersList(): LiveData<List<Character>>
    suspend fun getCharacterById(id: Int): CharacterInfo
    fun loadCharactersList(): Unit
    suspend fun loadCharacterById(id: Int, scope: CoroutineScope)
}