package com.example.marveltestapp.domain

import javax.inject.Inject

class LoadCharacterByIdUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    suspend fun loadCharacterById(id: Int) = repository.loadCharacterById(id)
}