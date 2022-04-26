package com.example.marveltestapp.domain

import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    suspend fun getCharacterById(id: Int): CharacterInfo = repository.getCharacterById(id)
}