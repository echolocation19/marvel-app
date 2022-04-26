package com.example.marveltestapp.data.network

import com.example.marveltestapp.data.network.model.CharactersContainerDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.nio.charset.StandardCharsets.UTF_8
import java.security.MessageDigest

interface ApiService {

    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query(QUERY_PARAM_TS) ts: String = tsProperty,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_PUBLIC_KEY,
        @Query(QUERY_PARAM_HASH) hash: String =
            getHashParam(tsProperty),
        @Query(QUERY_PARAM_LIMIT) limit: String = PARAM_LIMIT,
        @Query(QUERY_PARAM_ORDER_BY) orderBy: String = PARAM_ORDER_BY,
        @Query(QUERY_PARAM_OFFSET) offset: String = getRandomOffset(),
    ): CharactersContainerDto

    @GET("/v1/public/characters/{id}")
    suspend fun getCharacterById(
        @Path(PATH_PARAM_CHARACTER_ID) id: Int,
        @Query(QUERY_PARAM_TS) ts: String = tsInfoProperty,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_PUBLIC_KEY,
        @Query(QUERY_PARAM_HASH) hash: String =
            getHashParam(tsInfoProperty)
    ): CharactersContainerDto

    companion object {
        private const val QUERY_PARAM_API_KEY = "apikey"
        private const val QUERY_PARAM_TS = "ts"
        private const val QUERY_PARAM_HASH = "hash"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_ORDER_BY = "orderBy"
        private const val QUERY_PARAM_OFFSET = "offset"
        private const val PARAM_ORDER_BY = "-modified"
        private const val PATH_PARAM_CHARACTER_ID = "id"
        private const val PARAM_LIMIT = "20"
        private const val API_PUBLIC_KEY = "c72a862a396de63fb4bfe1f05c776471"
        private const val API_PRIVATE_KEY = "4f816f454cc1bd9e1b2b7d902497b870ff75947e"

        private fun getRandomOffset(): String {
            return (0..1000).random().toString()
        }

        private val tsProperty by lazy {
            System.currentTimeMillis().toString()
        }

        private val tsInfoProperty by lazy {
            System.currentTimeMillis().toString()
        }

        private fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }

        private fun md5(str: String): ByteArray =
            MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))

        private fun getHashParam(ts: String) = md5("${ts}${API_PRIVATE_KEY}${API_PUBLIC_KEY}").toHex()
    }

}
