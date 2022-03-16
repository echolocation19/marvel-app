package com.example.marveltestapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharactersList(list: List<CharacterDbModel>)

    @Query("SELECT * FROM all_characters_list")
    fun getCharactersList(): LiveData<List<CharacterDbModel>>

    @Query("SELECT * FROM all_characters_list WHERE characterId == :id LIMIT 1")
    fun getCharacter(id: Int): LiveData<CharacterDbModel>

}