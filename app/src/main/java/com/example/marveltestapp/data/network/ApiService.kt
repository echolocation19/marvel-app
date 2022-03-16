package com.example.marveltestapp.data.network

import com.example.marveltestapp.BuildConfig
import com.example.marveltestapp.data.network.model.CharactersContainerDto
import retrofit2.http.GET
import retrofit2.http.Query
import java.nio.charset.StandardCharsets.UTF_8
import java.security.MessageDigest

interface ApiService {

    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query(QUERY_PARAM_TS) ts: String = "1000",
        @Query(QUERY_PARAM_API_KEY) apiKey: String = BuildConfig.API_PUBLIC_KEY,
        @Query(QUERY_PARAM_HASH) hash: String =
            "091b4d6746d7a5280b7c0cde82e03ae6"
            //md5("1${BuildConfig.API_PRIVATE_KEY}${BuildConfig.API_PUBLIC_KEY}").toHex(),
    ): CharactersContainerDto

    @GET("/v1/public/characters")
    suspend fun getCharacterById(
        @Query(QUERY_PARAM_TS) ts: String = System.currentTimeMillis().toString(),
        @Query(QUERY_PARAM_API_KEY) apiKey: String = BuildConfig.API_PUBLIC_KEY,
        @Query(QUERY_PARAM_CHARACTER_ID) id: Int
    ): CharactersContainerDto


    companion object {
        private const val QUERY_PARAM_API_KEY = "apikey"
        private const val QUERY_PARAM_CHARACTER_ID = "characterId"
        private const val QUERY_PARAM_TS = "ts"
        private const val QUERY_PARAM_HASH = "hash"

        fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }

        private fun md5(str: String): ByteArray =
            MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))
    }

}
