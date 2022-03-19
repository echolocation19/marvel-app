package com.example.marveltestapp.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.marveltestapp.data.database.CharactersDatabase
import com.example.marveltestapp.data.repository.CharactersRepositoryImpl
import com.example.marveltestapp.domain.Character
import com.example.marveltestapp.domain.GetCharacterByIdUseCase
import com.example.marveltestapp.domain.GetCharactersListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val db = CharactersDatabase.getInstance(application)
    private val dao = db.charactersDao()
    private val repository = CharactersRepositoryImpl(dao)

    private val getCharactersListUseCase = GetCharactersListUseCase(repository)
    private val getCharacterByIdUseCase = GetCharacterByIdUseCase(repository)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadData()
        }
    }

    val charactersList = getCharactersListUseCase()

    fun getCharacterById(id: Int): LiveData<Character>  = getCharacterByIdUseCase(id)

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            //repository.loadCharacterById(id)
        }
    }


}