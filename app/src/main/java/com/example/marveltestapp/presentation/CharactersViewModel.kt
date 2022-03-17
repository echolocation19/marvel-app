package com.example.marveltestapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.marveltestapp.data.database.CharactersDatabase
import com.example.marveltestapp.data.repository.CharactersRepositoryImpl
import com.example.marveltestapp.domain.GetCharactersListUseCase
import kotlinx.coroutines.launch

class CharactersViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val db = CharactersDatabase.getInstance(application)
    private val dao = db.charactersDao()
    private val repository = CharactersRepositoryImpl(dao)

    private val getCharactersListUseCase = GetCharactersListUseCase(repository)

    init {
        viewModelScope.launch {
            repository.loadData()
        }
    }

    val charactersList = getCharactersListUseCase()
}