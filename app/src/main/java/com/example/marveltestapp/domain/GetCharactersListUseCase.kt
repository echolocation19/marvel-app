package com.example.marveltestapp.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    operator fun invoke(): LiveData<List<Character>> =
        repository.getCharactersList()
}