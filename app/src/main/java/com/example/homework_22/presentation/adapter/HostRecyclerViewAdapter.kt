package com.example.homework_22.presentation.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_22.databinding.ItemHostRecyclerLayoutBinding
import com.example.homework_22.presentation.model.PostPresentation
import com.example.homework_22.presentation.model.StoryPresentation

class HostRecyclerViewAdapter(
    private var stories: List<StoryPresentation>,
    private var posts: List<PostPresentation>,
    private val onPostClick: (PostPresentation) -> Unit
) : RecyclerView.Adapter<HostRecyclerViewAdapter.HostViewHolder>() {

    companion object {
        private const val VIEW_TYPE_STORIES = 0
        private const val VIEW_TYPE_POSTS = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_STORIES
            else -> VIEW_TYPE_POSTS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HostViewHolder {
        val binding = ItemHostRecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HostViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_STORIES -> holder.bindStories(stories)
            VIEW_TYPE_POSTS -> holder.bindPosts(posts, onPostClick)
        }
    }

    override fun getItemCount(): Int = 2

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newStories: List<StoryPresentation>, newPosts: List<PostPresentation>) {
        this.stories = newStories
        this.posts = newPosts
        notifyDataSetChanged()
    }

    inner class HostViewHolder(private val binding: ItemHostRecyclerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindStories(stories: List<StoryPresentation>) {
            val storyAdapter = StoryRecyclerViewAdapter()
            storyAdapter.submitList(stories)
            binding.storyRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = storyAdapter
            }
        }

        fun bindPosts(posts: List<PostPresentation>, onClick: (PostPresentation) -> Unit) {
            val postAdapter = PostRecyclerViewAdapter(onClick)
            binding.postRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = postAdapter
            }
            postAdapter.submitList(posts)
        }
    }
}