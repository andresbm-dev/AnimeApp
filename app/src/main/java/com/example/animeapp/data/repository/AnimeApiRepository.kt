package com.example.animeapp.data.repository

import com.example.animeapp.domain.models.AnimeTopScoreModelApi

interface AnimeApiRepository {

    suspend fun getAnimeTopScore( page : Int) : AnimeTopScoreModelApi?
}