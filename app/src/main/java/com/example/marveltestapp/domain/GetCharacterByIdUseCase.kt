package com.example.marveltestapp.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    fun getCharacterById(id: Int): LiveData<CharacterInfo> = repository.getCharacterById(id)
}