package com.example.animeapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.animeapp.core.ApplicationActivity.Companion.context
import com.example.animeapp.core.BaseListViewHolder
import com.example.animeapp.databinding.ItemCardAnimeTopBinding
import com.example.animeapp.domain.models.AnimeTopScoreModel

class CarrouselAnimeTopAdapter(
    private val onClick: (AnimeTopScoreModel) -> Unit,

): ListAdapter<AnimeTopScoreModel, BaseListViewHolder<*>>(DiffUtilCallback)  {

    private object DiffUtilCallback : DiffUtil.ItemCallback<AnimeTopScoreModel>() {
        override fun areItemsTheSame(oldItem: AnimeTopScoreModel, newItem: AnimeTopScoreModel): Boolean = oldItem.animeId == newItem.animeId
        override fun areContentsTheSame(oldItem: AnimeTopScoreModel, newItem: AnimeTopScoreModel): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListViewHolder<*> {
        val itemBinding = ItemCardAnimeTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseListViewHolder<*>, position: Int) {
        when (holder) {
            is BindViewHolder -> holder.bind(getItem(position), position)
        }
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, android.R.anim.fade_in);
        holder.itemView.startAnimation(animation)
    }

    inner class BindViewHolder(private val binding: ItemCardAnimeTopBinding) : BaseListViewHolder<AnimeTopScoreModel>(binding.root) {

        override fun bind(item: AnimeTopScoreModel, position: Int)  {
            Glide.with(context).load(item.animeImages?.animeJpg?.animeImage).into(binding.IvAnime)
            binding.IvAnime.setOnClickListener {
                startRotationAnimation(binding.IvAnime, newLambda = {  onClick(item)})
            }

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