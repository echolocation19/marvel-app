package com.example.marveltestapp.domain

class GetCharacterByIdUseCase(
    private val repository: CharactersRepository
) {
    operator fun invoke(id: Int) = repository.getCharacterById(id)
}