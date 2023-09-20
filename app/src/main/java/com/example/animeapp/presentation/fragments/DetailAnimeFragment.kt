package com.example.animeapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.animeapp.R
import com.example.animeapp.core.ApplicationActivity
import com.example.animeapp.databinding.FragmentDetailAnimeBinding
import com.example.animeapp.databinding.FragmentMainAnimeBinding
import com.example.animeapp.presentation.viewmodel.MainAnimeViewModel


class DetailAnimeFragment : Fragment() {

    private lateinit var binding: FragmentDetailAnimeBinding
    private lateinit var viewModel: MainAnimeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailAnimeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainAnimeViewModel::class.java]
        initObservers()
        initListeners()

    }

    private fun initListeners() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initObservers() {
        viewModel.animeSelectedLiveData.observe(viewLifecycleOwner){
            Glide.with(requireContext()).load(it.animeImages?.animeJpg?.animeImage)
                .apply( RequestOptions().override(1000, 850))
                .into(binding.ivAnimeDetail)
            binding.tvDescription.text = it.animeSynopsis
            binding.tvTitleAnime.text = it.animeTitle
            if (it.animeAiring == true)
                binding.onAir.text = "Al Aire"
            else
                binding.onAir.text = "Fuera de Servicio"
            it.animeScore?.let {rating->
                binding.rBar.rating = rating.toFloat() / 2
                binding.pointsRating.text = rating
            }

        }
    }


}