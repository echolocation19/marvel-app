package com.example.marveltestapp.domain

class GetCharacterByIdUseCase(
    private val repository: CharactersRepository
) {
    operator fun invoke(characterId: Int) = repository.getCharacterById(characterId)
}