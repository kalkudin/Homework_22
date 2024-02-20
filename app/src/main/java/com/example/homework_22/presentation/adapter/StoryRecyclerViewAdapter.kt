package com.example.homework_22.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.homework_22.databinding.ItemStoryLayoutBinding
import com.example.homework_22.presentation.model.StoryPresentation
import com.example.homework_22.presentation.util.loadImage

class StoryRecyclerViewAdapter :
    ListAdapter<StoryPresentation, StoryRecyclerViewAdapter.StoryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding =
            ItemStoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = getItem(position)
        holder.bind(story)
    }

    class StoryViewHolder(private val binding: ItemStoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: StoryPresentation) {
            with(binding) {
                tvTitle.text = story.title
                icImageContainer.loadImage(story.cover, 20)
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<StoryPresentation>() {
        override fun areItemsTheSame(
            oldItem: StoryPresentation,
            newItem: StoryPresentation
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: StoryPresentation,
            newItem: StoryPresentation
        ): Boolean =
            oldItem == newItem
    }
}