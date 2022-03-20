package com.example.marveltestapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.marveltestapp.data.database.CharactersDao
import com.example.marveltestapp.data.mapper.CharacterMapper
import com.example.marveltestapp.data.network.ApiService
import com.example.marveltestapp.data.worker.RefreshDataWorker
import com.example.marveltestapp.domain.Character
import com.example.marveltestapp.domain.CharacterInfo
import com.example.marveltestapp.domain.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val mapper: CharacterMapper,
    private val apiService: ApiService,
    private val charactersDao: CharactersDao,
    private val application: Application
) : CharactersRepository {

    override fun getCharactersList(): LiveData<List<Character>> {
        val charactersList = charactersDao.getCharactersList()
        return Transformations.map(charactersList) {
            it.map { dbModel ->
                mapper.mapDbModelToEntity(dbModel)
            }
        }
    }

    override fun getCharacterById(id: Int): LiveData<CharacterInfo> {
        return Transformations.map(charactersDao.getCharacter(id)) {
            mapper.mapDbInfoModelToEntity(it)
        }
    }

    override suspend fun loadCharacterById(id: Int) {
        val dto = apiService.getCharacterById(id)
        val character = mapper.mapCharactersContainerToListResult(dto)
        val result = character.map { mapper.mapDtoToDbInfoModel(it) }
        charactersDao.insertCharacter(result[0])
    }

    override fun loadCharactersList() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}