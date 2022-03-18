package com.example.marveltestapp.data.network

import com.example.marveltestapp.BuildConfig
import com.example.marveltestapp.data.network.model.CharactersContainerDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.nio.charset.StandardCharsets.UTF_8
import java.security.MessageDigest

interface ApiService {

    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query(QUERY_PARAM_TS) ts: String = System.currentTimeMillis().toString(),
        @Query(QUERY_PARAM_API_KEY) apiKey: String = BuildConfig.API_PUBLIC_KEY,
        @Query(QUERY_PARAM_HASH) hash: String =
            getHashParam(),
    ): CharactersContainerDto

    @GET("/v1/public/characters/{id}")
    suspend fun getCharacterById(
        @Query(QUERY_PARAM_TS) ts: String = System.currentTimeMillis().toString(),
        @Query(QUERY_PARAM_API_KEY) apiKey: String = BuildConfig.API_PUBLIC_KEY,
        @Query(QUERY_PARAM_HASH) hash: String =
            getHashParam(),
        @Path(PATH_PARAM_CHARACTER_ID) id: Int
    ): CharactersContainerDto


    companion object {
        private const val QUERY_PARAM_API_KEY = "apikey"
        private const val PATH_PARAM_CHARACTER_ID = "characterId"
        private const val QUERY_PARAM_TS = "ts"
        private const val QUERY_PARAM_HASH = "hash"

        private fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }

        private fun md5(str: String): ByteArray =
            MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))

        private fun getHashParam() = md5("${System.currentTimeMillis()}${BuildConfig.API_PRIVATE_KEY}${BuildConfig.API_PUBLIC_KEY}").toHex()

    }

}
