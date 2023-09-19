package com.example.animeapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeapp.databinding.FragmentMainAnimeBinding
import com.example.animeapp.presentation.adapters.AnimeDiscoverAdapter
import com.example.animeapp.presentation.adapters.CarrouselAnimeTopAdapter
import com.example.animeapp.presentation.viewmodel.MainAnimeViewModel


class MainAnimeFragment : Fragment() {

    private lateinit var binding: FragmentMainAnimeBinding
    private lateinit var viewModel: MainAnimeViewModel

    private val animeTopAdapter by lazy{
        CarrouselAnimeTopAdapter{
            findNavController().navigate(MainAnimeFragmentDirections.actionMainAnimeFragmentToDetailAnimeFragment())
        }
    }
    private val animeDiscoverAdapter by lazy{
        AnimeDiscoverAdapter{
            findNavController().navigate(MainAnimeFragmentDirections.actionMainAnimeFragmentToDetailAnimeFragment())
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainAnimeBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(requireActivity())[MainAnimeViewModel::class.java]

        setupRecyclerView()
        initObservers()
        setupListeners()

    }

    private fun setupListeners() {
        binding.searchView.setOnClickListener {
            findNavController().navigate(MainAnimeFragmentDirections.actionMainAnimeFragmentToSearchFragment())
        }
    }

    private fun initObservers() {
        viewModel.animeTopListLiveData.observe(viewLifecycleOwner){
            animeTopAdapter.submitList(it)
        }
        viewModel.animeDiscoverListLiveData.observe(viewLifecycleOwner){
            animeDiscoverAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        binding.rvAnimeTopScore.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            adapter = animeTopAdapter
        }
        binding.rvAnimeDiscover.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = animeDiscoverAdapter
        }
    }


}