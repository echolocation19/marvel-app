package com.example.marveltestapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CharacterViewModelFactory(
    private val application: Application,
    private val id: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java))
            return CharacterViewModel(application, id) as T
        else
            throw RuntimeException("Unknown ViewModel class $modelClass")
    }

}