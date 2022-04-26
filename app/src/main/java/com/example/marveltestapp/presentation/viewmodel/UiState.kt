package com.example.marveltestapp.presentation.viewmodel

import com.example.marveltestapp.domain.CharacterInfo

sealed class UiState {
    object Loading : UiState()
    data class Success(val characterInfo: CharacterInfo) : UiState()
    data class Error(val message: String) : UiState()
}