package com.example.marveltestapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marveltestapp.domain.Character

class CharactersViewModel : ViewModel() {

    val shopListLiveData = MutableLiveData<List<Character>>()

    init {
        val charactersList = listOf(
            Character(1, "spider-man", "14.01.02"),
            Character(2, "spider-man", "14.01.02"),
            Character(3, "spider-man", "14.01.02"),
            Character(4, "spider-man", "14.01.02"),
            Character(5, "spider-man", "14.01.02"),
            Character(6, "spider-man", "14.01.02"),
            Character(7, "spider-man", "14.01.02")
        )
        shopListLiveData.value = charactersList
    }

}