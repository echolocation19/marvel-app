package com.example.marveltestapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marveltestapp.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase,
    private val loadCharactersListUseCase: LoadCharactersListUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val loadCharacterByIdUseCase: LoadCharacterByIdUseCase
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadCharactersListUseCase.invoke()
        }
    }

    val charactersList = getCharactersListUseCase.invoke()
}