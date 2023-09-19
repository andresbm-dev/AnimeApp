package com.example.animeapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.animeapp.R
import com.example.animeapp.databinding.ActivityMainAnimeBinding
import com.example.animeapp.presentation.viewmodel.MainAnimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainAnimeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainAnimeBinding
    private  val viewModel : MainAnimeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        viewModel.getAnimeTopScore()

    }


}