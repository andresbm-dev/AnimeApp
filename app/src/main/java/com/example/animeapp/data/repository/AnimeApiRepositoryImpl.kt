package com.example.animeapp.data.repository

import android.util.Log
import com.example.animeapp.data.remote.ApiService
import com.example.animeapp.domain.models.AnimeTopScoreModelApi
import javax.inject.Inject

class AnimeApiRepositoryImpl @Inject constructor(private val apiService: ApiService):AnimeApiRepository{

    override suspend fun getAnimeTopScore( page:Int ) : AnimeTopScoreModelApi? {
        runCatching {
       apiService.getAnimeTopScore("score","desc", null, page = page.toString())
        }.onSuccess {
                return it.body()
        }.onFailure {
            Log.i("abm", "Ha ocurrido un error ${it.message}")
        }
        return null
    }
}