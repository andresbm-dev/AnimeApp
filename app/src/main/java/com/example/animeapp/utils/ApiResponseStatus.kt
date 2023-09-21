package com.example.animeapp.utils

sealed class ApiResponseStatus<T> {
    class Success<T>(val data: T): ApiResponseStatus<T>()
    class Error<T>(val messageId:String): ApiResponseStatus<T>()
}