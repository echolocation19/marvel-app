package com.example.marveltestapp.di

import android.content.Context
import com.example.marveltestapp.data.database.CharactersDao
import com.example.marveltestapp.data.database.CharactersDatabase
import com.example.marveltestapp.data.mapper.CharacterMapper
import com.example.marveltestapp.data.network.ApiFactory
import com.example.marveltestapp.data.network.ApiService
import com.example.marveltestapp.data.repository.CharactersRepositoryImpl
import com.example.marveltestapp.domain.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideCoinInfoDao(
        @ApplicationContext ctx: Context
    ): CharactersDao {
        return CharactersDatabase.getInstance(ctx).charactersDao()
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiFactory.apiService
    }

    @Provides
    @Singleton
    fun provideMapper(): CharacterMapper {
        return CharacterMapper()
    }

    @Provides
    @Singleton
    fun provideCharactersRepository(
        characterMapper: CharacterMapper,
        apiService: ApiService,
        charactersDao: CharactersDao
    ): CharactersRepository {
        return CharactersRepositoryImpl(characterMapper, apiService, charactersDao)
    }

}