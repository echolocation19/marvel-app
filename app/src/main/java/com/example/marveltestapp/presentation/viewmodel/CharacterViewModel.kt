package com.example.marveltestapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.marveltestapp.data.database.CharactersDatabase
import com.example.marveltestapp.data.repository.CharactersRepositoryImpl
import com.example.marveltestapp.domain.CharacterInfo
import com.example.marveltestapp.domain.GetCharacterByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CharacterViewModel(
    application: Application,
    val id: Int
) : AndroidViewModel(application) {

    private val db = CharactersDatabase.getInstance(application)
    private val dao = db.charactersDao()
    private val repository = CharactersRepositoryImpl(dao)

    private val getCharacterByIdUseCase = GetCharacterByIdUseCase(repository)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadCharacterById(id)
        }
    }

    fun getCharacter(): LiveData<CharacterInfo> {
        return getCharacterByIdUseCase.invoke(id)
    }

}