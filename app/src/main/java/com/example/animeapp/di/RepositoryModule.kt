package com.example.animeapp.di

import com.example.animeapp.data.repository.AnimeApiRepository
import com.example.animeapp.data.repository.AnimeApiRepositoryImpl
import com.example.animeapp.domain.usecase.GetAnimeTopUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract  class RepositoryModule {

    @Binds
    abstract fun providesAnimeRepository(repositoryImpl: AnimeApiRepositoryImpl): AnimeApiRepository

}