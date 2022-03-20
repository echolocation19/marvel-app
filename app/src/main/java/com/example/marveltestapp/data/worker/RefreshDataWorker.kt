package com.example.marveltestapp.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.marveltestapp.data.database.CharactersDao
import com.example.marveltestapp.data.mapper.CharacterMapper
import com.example.marveltestapp.data.network.ApiService
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val charactersDao: CharactersDao,
    private val apiService: ApiService,
    private val mapper: CharacterMapper
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val dto =  apiService.getAllCharacters()
                val charactersList = mapper.mapCharactersContainerToListResult(dto)
                val resultList = charactersList.map { mapper.mapDtoToDbModel(it) }
                charactersDao.insertCharactersList(resultList)
            } catch (e: Exception) {
            }
            delay(DELAY_TIME)
        }
    }

    companion object {
        const val NAME = "RefreshDataWorker"
        const val DELAY_TIME = 10000L
        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}