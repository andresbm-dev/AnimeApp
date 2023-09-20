package com.example.animeapp.presentation.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        binding.rvAnimeDiscover.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE){
                    viewModel.getAnimeDiscover()
                }
            }
        })
        binding.rvAnimeTopScore.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollHorizontally(1) && newState == RecyclerView.SCROLL_STATE_IDLE){
                    viewModel.getAnimeTopScore()
                }
            }
        })
    }

    private fun initObservers() {
        viewModel.animeTopListLiveData.observe(viewLifecycleOwner){
            animeTopAdapter.submitList(it?.toMutableList())
        }
        viewModel.animeDiscoverListLiveData.observe(viewLifecycleOwner){
            animeDiscoverAdapter.submitList(it?.toMutableList())
        }
        viewModel.progressApiLiveData.observe(viewLifecycleOwner){
            if (it){
                binding.tvTitle.visibility = View.VISIBLE
                binding.progressApi.visibility = View.VISIBLE
            }else{
                binding.progressApi.visibility = View.GONE
            }
        }
        viewModel.progressAnimeTopLiveData.observe(viewLifecycleOwner){
            if (it){
                binding.progressAnimeTop.visibility = View.VISIBLE
            }else{
                binding.progressAnimeTop.visibility = View.GONE
            }
        }
        viewModel.progressAnimeDiscoverLiveData.observe(viewLifecycleOwner){
            if (it){
                binding.progressAnimeDiscover.visibility = View.VISIBLE
            }else{
                binding.progressAnimeDiscover.visibility = View.GONE
            }
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