package com.example.animeapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.domain.models.AnimeTopScoreModel
import com.example.animeapp.domain.usecase.GetAnimeTopUseCase
import com.example.animeapp.utils.ApiResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainAnimeViewModel @Inject constructor(private val useCase: GetAnimeTopUseCase) :
    ViewModel() {

    private var _animeTopList: MutableLiveData<List<AnimeTopScoreModel>?> = MutableLiveData()
    val animeTopListLiveData: LiveData<List<AnimeTopScoreModel>?>
        get() = _animeTopList

    private var animeDiscoverList: MutableList<AnimeTopScoreModel>? = mutableListOf()
    private var animeTopList: MutableList<AnimeTopScoreModel>? = mutableListOf()

    private var _animeDiscoverList: MutableLiveData<List<AnimeTopScoreModel>?> = MutableLiveData()
    val animeDiscoverListLiveData: LiveData<List<AnimeTopScoreModel>?>
        get() = _animeDiscoverList
    private var _progressApi: MutableLiveData<Boolean> = MutableLiveData()
    val progressApiLiveData: LiveData<Boolean>
        get() = _progressApi
    private var _progressAnimeTop: MutableLiveData<Boolean> = MutableLiveData()
    val progressAnimeTopLiveData: LiveData<Boolean>
        get() = _progressAnimeTop
    private var _progressAnimeDiscover: MutableLiveData<Boolean> = MutableLiveData()
    val progressAnimeDiscoverLiveData: LiveData<Boolean>
        get() = _progressAnimeDiscover

    private var _animeSelected: MutableLiveData<AnimeTopScoreModel> = MutableLiveData()
    val animeSelectedLiveData: LiveData<AnimeTopScoreModel>
        get() = _animeSelected


    private var page = 0
    private var pageAnimeTop = 0
    init {
        _progressApi.postValue(true)
        getAnimeTopScore()
        getAnimeDiscover()

    }
    fun getAnimeTopScore() {
        viewModelScope.launch {
            pageAnimeTop++
            if (pageAnimeTop>1){
                _progressAnimeTop.postValue(true)
            }
            withContext(Dispatchers.IO){
                when(val response = useCase.invoke(pageAnimeTop)){
                    is ApiResponseStatus.Error -> TODO()
                    is ApiResponseStatus.Success -> {
                        response.data?.animeData?.forEach {
                            animeTopList?.add(it)
                        }
                        _animeTopList.postValue(animeTopList)
                    }
                }
            }
            _progressAnimeTop.postValue(false)

        }
    }
     fun getAnimeDiscover() {
        viewModelScope.launch {
            page++
            if (page>1){
                _progressAnimeDiscover.postValue(true)
            }
            withContext(Dispatchers.IO){
                when(val response = useCase.getAnimeDiscover(page)){
                    is ApiResponseStatus.Error -> TODO()
                    is ApiResponseStatus.Success -> {
                        response.data?.animeData?.forEach {
                            animeDiscoverList?.add(it)
                        }
                    }
                }

            }
            _animeDiscoverList.postValue(animeDiscoverList)
            _progressAnimeDiscover.postValue(false)
            if (page==1)
                _progressApi.postValue(false)
        }
    }

    fun getAnimeSelected(animeSelected : AnimeTopScoreModel){
        _animeSelected.postValue(animeSelected)
    }

}