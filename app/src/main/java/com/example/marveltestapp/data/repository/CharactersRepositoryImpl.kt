package com.example.marveltestapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.marveltestapp.data.database.CharactersDao
import com.example.marveltestapp.data.mapper.CharacterMapper
import com.example.marveltestapp.data.network.ApiService
import com.example.marveltestapp.data.network.model.CharactersContainerDto
import com.example.marveltestapp.data.worker.RefreshDataWorker
import com.example.marveltestapp.domain.Character
import com.example.marveltestapp.domain.CharacterInfo
import com.example.marveltestapp.domain.CharactersRepository
import kotlinx.coroutines.*
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

    override suspend fun getCharacterById(id: Int): CharacterInfo {
        val character = withContext(Dispatchers.IO) {
            charactersDao.getCharacter(id)
        }
        return mapper.mapDbInfoModelToEntity(character)
    }

    override suspend fun loadCharacterById(id: Int, scope: CoroutineScope) {
        scope.launch {
            try {
                var dto: CharactersContainerDto = async(Dispatchers.IO) {
                        apiService.getCharacterById(id)
                }.await()
                var character = async(Dispatchers.Default) {
                        mapper.mapCharactersContainerToListResult(dto)
                }.await()
                val result = async(Dispatchers.Default) {
                        character.map { mapper.mapDtoToDbInfoModel(it) }
                }.await()
                withContext(Dispatchers.IO) {
                    charactersDao.insertCharacter(result[0])
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.join()
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