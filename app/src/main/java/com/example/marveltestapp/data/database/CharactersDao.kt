package com.example.marveltestapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.marveltestapp.data.database.entity.CharacterDbModel
import com.example.marveltestapp.data.database.entity.CharacterInfoDbModel

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharactersList(list: List<CharacterDbModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterInfoDbModel)

    @Query("SELECT * FROM all_characters_list")
    fun getCharactersList(): LiveData<List<CharacterDbModel>>

    @Query("DELETE FROM all_characters_list")
    fun deleteCharactersList()

    @Query("SELECT * FROM all_characters_list WHERE id = :id LIMIT 1")
    fun getCharacter(id: Int): LiveData<CharacterInfoDbModel>
}