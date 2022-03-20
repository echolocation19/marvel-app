package com.example.marveltestapp.domain

import javax.inject.Inject

class GetCharacterByIdUseCase  @Inject constructor(
    private val repository: CharactersRepository
) {
    fun getCharacterById(id: Int) = repository.getCharacterById(id)
}