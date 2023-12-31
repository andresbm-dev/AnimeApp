package com.example.animeapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.animeapp.core.ApplicationActivity
import com.example.animeapp.core.BaseListViewHolder
import com.example.animeapp.databinding.ItemCardAnimeDiscoverBinding
import com.example.animeapp.domain.models.AnimeTopScoreModel

class AnimeDiscoverAdapter(
    private val onAnimeDiscoverClick: (AnimeTopScoreModel) -> Unit,
) : ListAdapter<AnimeTopScoreModel, BaseListViewHolder<*>>(DiffUtilCallback) {

    private object DiffUtilCallback : DiffUtil.ItemCallback<AnimeTopScoreModel>() {
        override fun areItemsTheSame(
            oldItem: AnimeTopScoreModel,
            newItem: AnimeTopScoreModel
        ): Boolean = oldItem.animeId == newItem.animeId

        override fun areContentsTheSame(
            oldItem: AnimeTopScoreModel,
            newItem: AnimeTopScoreModel
        ): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListViewHolder<*> {
        val itemBinding =
            ItemCardAnimeDiscoverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseListViewHolder<*>, position: Int) {
        when (holder) {
            is BindViewHolder -> holder.bind(getItem(position), position)
        }
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, android.R.anim.fade_out);
        holder.itemView.startAnimation(animation)
    }

    inner class BindViewHolder(private val binding: ItemCardAnimeDiscoverBinding) :
        BaseListViewHolder<AnimeTopScoreModel>(binding.root) {

        override fun bind(item: AnimeTopScoreModel, position: Int) {
            Glide.with(ApplicationActivity.context).load(item.animeImages?.animeJpg?.animeImage)
                .into(binding.ivAnime)
            binding.ivAnime.setOnClickListener {
                startRotationAnimation(binding.ivAnime, newLambda = {onAnimeDiscoverClick(item)})
            }
            binding.containerCard.setOnClickListener {
                startRotationAnimation(binding.ivAnime, newLambda = {onAnimeDiscoverClick(item)})
            }
            binding.titleAnime.text = item.animeTitle
            binding.tvDuration.text = item.animeDuration
            if (item.animeSeason != null)
                binding.tvAnimeSeason.text = item.animeSeason
            else
                binding.tvAnimeSeason.text = "Temporada no disponible"
        }
        private fun startRotationAnimation(view: View, newLambda:()->Unit){
            view.animate().apply {
                duration = 500
                interpolator = LinearInterpolator()
                rotationBy(360f)
                withEndAction { newLambda() }
                start()
            }
        }
    }
}