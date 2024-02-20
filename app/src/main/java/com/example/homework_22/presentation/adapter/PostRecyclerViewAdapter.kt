package com.example.homework_22.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.homework_22.databinding.ItemPostLayoutBinding
import com.example.homework_22.presentation.layout_manager.CustomLayoutManager
import com.example.homework_22.presentation.model.PostPresentation
import com.example.homework_22.presentation.util.loadImage

class PostRecyclerViewAdapter(
    private val onClick: (PostPresentation) -> Unit
) : ListAdapter<PostPresentation, PostRecyclerViewAdapter.PostViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    inner class PostViewHolder(
        private val binding: ItemPostLayoutBinding,
        private val onClick: (PostPresentation) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    onClick(item)
                }
            }
        }

        fun bind(post: PostPresentation) {
            with(binding) {
                icUserIcon.loadImage(post.profile, 300)

                tvUserName.text = post.userName
                tvDate.text = post.postDate
                tvDescription.text = post.title
                tvComments.text = post.comments
                tvLikes.text = post.likes

                val customLayoutManager = CustomLayoutManager()
                imageContainer.layoutManager = customLayoutManager

                val imagesAdapter = ImageRecyclerViewAdapter()
                imageContainer.adapter = imagesAdapter

                imagesAdapter.submitList(post.images)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<PostPresentation>() {
            override fun areItemsTheSame(oldItem: PostPresentation, newItem: PostPresentation): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PostPresentation, newItem: PostPresentation): Boolean =
                oldItem == newItem
        }
    }
}