package com.example.marveltestapp.di

import com.example.marveltestapp.domain.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetCharacterByIdUseCase(
        repository: CharactersRepository,
    ): GetCharacterByIdUseCase =
        GetCharacterByIdUseCase(repository)

    @Provides
    fun provideGetCharactersListUseCase(
        repository: CharactersRepository,
    ): GetCharactersListUseCase =
        GetCharactersListUseCase(repository)

    @Provides
    fun provideLoadCharactersListUseCase(
        repository: CharactersRepository,
    ): LoadCharactersListUseCase =
        LoadCharactersListUseCase(repository)

    @Provides
    fun provideLoadCharacterByIdUseCase(
        repository: CharactersRepository,
    ): LoadCharacterByIdUseCase =
        LoadCharacterByIdUseCase(repository)

}