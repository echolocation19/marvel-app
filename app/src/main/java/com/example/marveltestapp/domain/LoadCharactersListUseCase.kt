package com.example.marveltestapp.domain

import javax.inject.Inject

class LoadCharactersListUseCase @Inject constructor (
    private val repository: CharactersRepository
) {
    suspend operator fun invoke() = repository.loadCharactersList()
}