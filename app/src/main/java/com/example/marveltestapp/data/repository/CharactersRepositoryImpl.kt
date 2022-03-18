package com.example.marveltestapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.marveltestapp.data.database.CharactersDao
import com.example.marveltestapp.data.mapper.CharacterMapper
import com.example.marveltestapp.data.network.ApiFactory
import com.example.marveltestapp.domain.Character
import com.example.marveltestapp.domain.CharactersRepository

class CharactersRepositoryImpl(
    private val charactersDao: CharactersDao
) : CharactersRepository {

    private val mapper = CharacterMapper()
    private val apiService = ApiFactory.apiService

    suspend fun loadData() {
        val dto =  apiService.getAllCharacters()
        val charactersList = mapper.mapCharactersContainerToListResult(dto)
        val resultList = charactersList.map { mapper.mapDtoToDbModel(it) }
        charactersDao.insertCharactersList(resultList)
    }

    override fun getCharactersList(): LiveData<List<Character>> {
        val charactersList = charactersDao.getCharactersList()
        return Transformations.map(charactersList) {
            it.map { dbModel ->
                mapper.mapDbModelToEntity(dbModel)
            }
        }
    }

    override fun getCharacterById(id: Int): LiveData<Character> {
        val character = charactersDao.getCharacter(id)
        return Transformations.map(character) {
            mapper.mapDbModelToEntity(it)
        }
    }

}