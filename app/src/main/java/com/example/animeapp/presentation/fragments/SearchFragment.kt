package com.example.animeapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeapp.databinding.FragmentSearchBinding
import com.example.animeapp.domain.models.AnimeTopScoreModel
import com.example.animeapp.presentation.adapters.CarrouselAnimeTopAdapter
import com.example.animeapp.presentation.viewmodel.MainAnimeViewModel


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: MainAnimeViewModel
    private var animeTopScore = mutableListOf<AnimeTopScoreModel>()

    private val animeTopAdapter by lazy{
        CarrouselAnimeTopAdapter{anime->

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainAnimeViewModel::class.java]

        binding.rvAnimeTopScore.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = animeTopAdapter
        }
        initListeners()
        initObservers()

    }

    private fun initObservers() {
        viewModel.animeTopListLiveData.observe(viewLifecycleOwner){
            animeTopScore = it as MutableList<AnimeTopScoreModel>
        }
    }

    private fun initListeners() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.containerView.setOnClickListener {
            hideKeyboard()
        }


        binding.etSearch.addTextChangedListener { filter ->
            if (animeTopScore.isNotEmpty()) {
                val countriesFilters: List<AnimeTopScoreModel> = animeTopScore.filter { anime ->
                    anime.animeTitle?.lowercase()
                        ?.contains(filter.toString().lowercase())!!
                }
                if (countriesFilters.isNotEmpty() && !filter.isNullOrEmpty()){
                    binding.rvAnimeTopScore.visibility = View.VISIBLE
                    animeTopAdapter.submitList(countriesFilters.toMutableList())
                    binding.tvSearchResult.visibility = View.GONE
                }else if (filter.isNullOrEmpty()){
                    binding.rvAnimeTopScore.visibility = View.GONE
                    binding.tvSearchResult.text = "Ingresa Busqueda"
                    binding.tvSearchResult.visibility = View.VISIBLE
                }else{
                    binding.tvSearchResult.text = "No se encuentran resultados"
                    binding.tvSearchResult.visibility = View.VISIBLE
                    binding.rvAnimeTopScore.visibility = View.GONE

                }
            }
        }
    }

    private fun hideKeyboard() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }


}