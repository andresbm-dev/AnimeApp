package com.example.animeapp.core

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationActivity : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}