package com.example.marveltestapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CharacterDbModel::class],
    version = 2,
    exportSchema = false
)
abstract class CharactersDatabase: RoomDatabase() {

    abstract fun charactersDao(): CharactersDao

    companion object {

        private var db: CharactersDatabase? = null
        private const val DB_NAME = "characters.db"
        private val LOCK = Any()

        fun getInstance(context: Context): CharactersDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    CharactersDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
    }
}