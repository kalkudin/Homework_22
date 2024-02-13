package com.example.homework_22.presentation.adapter

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.homework_22.databinding.ItemPostLayoutBinding
import com.example.homework_22.presentation.model.PostPresentation
import com.google.android.flexbox.FlexboxLayout

class PostRecyclerViewAdapter :
    ListAdapter<PostPresentation, PostRecyclerViewAdapter.PostViewHolder>(DiffCallback) {

    inner class PostViewHolder(private val binding: ItemPostLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostPresentation) {
            with(binding) {
                Glide.with(icUserIcon.context)
                    .load(post.profile)
                    .transform(CenterCrop(), RoundedCorners(300))
                    .into(icUserIcon)

                tvUserName.text = "${post.firstName} ${post.lastName}"
                tvDate.text = post.postDate
                tvDescription.text = post.title
                tvComments.text = post.comments.toString() + " " + "Comments"
                tvLikes.text = post.likes.toString() + " " + "Likes"

                imageContainer.removeAllViews()

                post.images.forEach { imageUrl ->
                    addImageToFlexbox(imageUrl, imageContainer.context, imageContainer)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding =
            ItemPostLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<PostPresentation>() {
            override fun areItemsTheSame(oldItem: PostPresentation, newItem: PostPresentation): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PostPresentation, newItem: PostPresentation): Boolean {
                return oldItem == newItem
            }
        }
    }

    private fun addImageToFlexbox(imageUrl: String, context: Context, flexboxLayout: FlexboxLayout) {
        val imageView = ImageView(context).apply {
            Glide.with(this)
                .load(imageUrl)
                .override(250, 250)
                .transform(CenterCrop(), RoundedCorners(16))
                .centerCrop()
                .into(this)

            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        val layoutParams = FlexboxLayout.LayoutParams(
            250,
            250
        ).apply {
            setMargins(16, 16, 16, 16)
        }
        imageView.layoutParams = layoutParams
        imageView.layoutParams = layoutParams
        flexboxLayout.addView(imageView)
    }
}