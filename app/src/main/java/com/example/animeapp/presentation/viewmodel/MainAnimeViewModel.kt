package com.example.animeapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.domain.models.AnimeTopScoreModel
import com.example.animeapp.domain.models.AnimeTopScoreModelApi
import com.example.animeapp.domain.usecase.GetAnimeTopUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainAnimeViewModel @Inject constructor(private val useCase: GetAnimeTopUseCase) :
    ViewModel() {

    private var _animeTopList: MutableLiveData<List<AnimeTopScoreModel>?> = MutableLiveData()
    val animeTopListLiveData: LiveData<List<AnimeTopScoreModel>?>
        get() = _animeTopList

    private var _animeDiscoverList: MutableLiveData<List<AnimeTopScoreModel>?> = MutableLiveData()
    val animeDiscoverListLiveData: LiveData<List<AnimeTopScoreModel>?>
        get() = _animeDiscoverList
    init {
        getAnimeTopScore()
        getAnimeDiscover()
    }
    fun getAnimeTopScore() {
        viewModelScope.launch {
            val response = useCase.invoke(1)
            _animeTopList.postValue(response?.animeData)
        }
    }
    private fun getAnimeDiscover() {
        viewModelScope.launch {
            val response = useCase.getAnimeDiscover(1)
            _animeDiscoverList.postValue(response?.animeData)
        }
    }
}