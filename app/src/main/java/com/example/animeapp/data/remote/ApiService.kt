package com.example.animeapp.data.remote

import com.example.animeapp.domain.models.AnimeTopScoreModel
import com.example.animeapp.domain.models.AnimeTopScoreModelApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v4/anime")
    suspend fun getAnimeTopScore(
        @Query("order_by") orderBy: String?,
        @Query("sort") sort: String?,
        @Query("letter") letter: String?,
        @Query("page") page: String?
    ): Response<AnimeTopScoreModelApi>
}