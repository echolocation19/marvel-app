package com.example.marveltestapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marveltestapp.domain.CharacterInfo
import com.example.marveltestapp.domain.GetCharacterByIdUseCase
import com.example.marveltestapp.domain.LoadCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val loadCharacterByIdUseCase: LoadCharacterByIdUseCase
) : ViewModel() {

    fun loadCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            loadCharacterByIdUseCase.loadCharacterById(id)
        }
    }

    fun getCharacter(id: Int): LiveData<CharacterInfo> {
        return getCharacterByIdUseCase.getCharacterById(id)
    }

}