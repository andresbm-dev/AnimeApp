package com.example.animeapp.domain.usecase

import com.example.animeapp.data.repository.AnimeApiRepository
import com.example.animeapp.domain.models.AnimeTopScoreModelApi
import com.example.animeapp.utils.ApiResponseStatus
import javax.inject.Inject

class GetAnimeTopUseCase @Inject constructor(private val animeApiRepositoryImpl: AnimeApiRepository){
    suspend fun  invoke(page :Int ) : ApiResponseStatus<AnimeTopScoreModelApi?> {
        return animeApiRepositoryImpl.getAnimeTopScore(page)
    }
    suspend fun  getAnimeDiscover(page :Int ) : ApiResponseStatus<AnimeTopScoreModelApi?> {
        return animeApiRepositoryImpl.getAnimeDiscover(page)
    }

}