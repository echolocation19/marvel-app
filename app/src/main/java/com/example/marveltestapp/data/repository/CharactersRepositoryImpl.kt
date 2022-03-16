package com.example.marveltestapp.data.repository

import androidx.lifecycle.LiveData
import com.example.marveltestapp.data.database.CharactersDao
import com.example.marveltestapp.domain.Character
import com.example.marveltestapp.domain.CharactersRepository

class CharactersRepositoryImpl(
    private val charactersDao: CharactersDao
) : CharactersRepository {
    override fun getCharactersList(): LiveData<List<Character>> {
        TODO("Not yet implemented")
    }

}