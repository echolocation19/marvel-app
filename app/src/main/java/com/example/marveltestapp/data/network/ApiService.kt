package com.example.marveltestapp.data.network

import com.example.marveltestapp.BuildConfig
import com.example.marveltestapp.data.network.model.CharactersDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = BuildConfig.API_PRIVATE_KEY
    ): CharactersDto

    @GET("/v1/public/characters")
    suspend fun getCharacterById(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = BuildConfig.API_PRIVATE_KEY,
        @Query(QUERY_PARAM_CHARACTER_ID) id: Int
    ): CharactersDto


    companion object {
        private const val QUERY_PARAM_API_KEY = "apikey"
        private const val QUERY_PARAM_CHARACTER_ID = "characterId"
    }

}
