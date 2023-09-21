package com.example.animeapp.data.repository

import com.example.animeapp.domain.models.AnimeTopScoreModelApi
import com.example.animeapp.utils.ApiResponseStatus

interface AnimeApiRepository {

    suspend fun getAnimeTopScore( page : Int) : ApiResponseStatus<AnimeTopScoreModelApi?>
    suspend fun getAnimeDiscover( page : Int) : ApiResponseStatus<AnimeTopScoreModelApi?>

}