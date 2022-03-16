package com.example.marveltestapp.domain

import androidx.lifecycle.LiveData

interface CharactersRepository {

    fun getCharactersList(): LiveData<List<Character>>

}