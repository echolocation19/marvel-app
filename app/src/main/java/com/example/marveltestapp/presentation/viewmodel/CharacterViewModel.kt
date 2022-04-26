package com.example.marveltestapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.marveltestapp.domain.GetCharacterByIdUseCase
import com.example.marveltestapp.domain.LoadCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val loadCharacterByIdUseCase: LoadCharacterByIdUseCase
) : BaseViewModel<UiState>() {

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            try {
                loadCharacterByIdUseCase.loadCharacterById(id, this)
                val info = getCharacterByIdUseCase.getCharacterById(id)
                uiState.value = UiState.Success(info)
            } catch (e: Exception) {
                uiState.value = UiState.Error("Network Failed")
            }
        }
    }

}