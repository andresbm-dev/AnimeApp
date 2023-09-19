package com.example.animeapp.domain.usecase

import com.example.animeapp.data.repository.AnimeApiRepository
import com.example.animeapp.domain.models.AnimeTopScoreModelApi
import javax.inject.Inject

class GetAnimeTopUseCase @Inject constructor(private val animeApiRepositoryImpl: AnimeApiRepository){
    suspend fun  invoke(page :Int ) : AnimeTopScoreModelApi? {
        return animeApiRepositoryImpl.getAnimeTopScore(page)
    }
    suspend fun  getAnimeDiscover(page :Int ) : AnimeTopScoreModelApi? {
        return animeApiRepositoryImpl.getAnimeDiscover(page)
    }

}