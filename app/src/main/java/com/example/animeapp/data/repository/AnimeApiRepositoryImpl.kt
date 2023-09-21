package com.example.animeapp.data.repository

import android.util.Log
import com.example.animeapp.data.remote.ApiService
import com.example.animeapp.domain.models.AnimeTopScoreModelApi
import com.example.animeapp.utils.ApiResponseStatus
import javax.inject.Inject

class AnimeApiRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    AnimeApiRepository {

    override suspend fun getAnimeTopScore(page: Int): ApiResponseStatus<AnimeTopScoreModelApi?> {
        runCatching {
            apiService.getAnimeTopScore("score", "desc", null, page = page.toString())
        }.onSuccess {
            return ApiResponseStatus.Success(it.body())
        }.onFailure {
            Log.i("abm", "Ha ocurrido un error ${it.message}")
        }
        return ApiResponseStatus.Error("Error")
    }

    override suspend fun getAnimeDiscover(page: Int): ApiResponseStatus<AnimeTopScoreModelApi?> {
        runCatching {
            apiService.getAnimeTopScore(null, null, null, page = page.toString())
        }.onSuccess {
            return ApiResponseStatus.Success(it.body())
        }.onFailure {
            Log.i("abm", "Ha ocurrido un error ${it.message}")
        }
        return ApiResponseStatus.Error("Error")
    }
}