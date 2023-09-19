package com.example.animeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.domain.usecase.GetAnimeTopUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainAnimeViewModel @Inject constructor(private val useCase: GetAnimeTopUseCase) :
    ViewModel() {

    fun getAnimeTopScore() {
        viewModelScope.launch {
            useCase.invoke()
        }
    }
}