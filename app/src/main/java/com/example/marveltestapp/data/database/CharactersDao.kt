package com.example.marveltestapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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

    @Query("SELECT * FROM all_characters_list WHERE id = :id LIMIT 1")
    fun getCharacter(id: Int): LiveData<CharacterInfoDbModel>
}