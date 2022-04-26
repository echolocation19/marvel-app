package com.example.marveltestapp.domain

import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class LoadCharacterByIdUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    suspend fun loadCharacterById(id: Int, scope: CoroutineScope) = repository.loadCharacterById(id, scope)
}