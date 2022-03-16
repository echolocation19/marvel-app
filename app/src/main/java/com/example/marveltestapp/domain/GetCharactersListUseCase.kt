package com.example.marveltestapp.domain

class GetCharactersListUseCase(
    private val repository: CharactersRepository
) {

    operator fun invoke() =
        repository.getCharactersList()
}