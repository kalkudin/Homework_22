package com.example.homework_22.presentation.post_details

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.homework_22.databinding.FragmentPostDetailsLayoutBinding
import com.example.homework_22.presentation.adapter.HostRecyclerViewAdapter
import com.example.homework_22.presentation.adapter.ImageRecyclerViewAdapter
import com.example.homework_22.presentation.base.BaseFragment
import com.example.homework_22.presentation.event.PostDetailsEvent
import com.example.homework_22.presentation.layout_manager.CustomLayoutManager
import com.example.homework_22.presentation.model.DetailsState
import com.example.homework_22.presentation.model.PostPresentation
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostDetailsFragment : BaseFragment<FragmentPostDetailsLayoutBinding>(FragmentPostDetailsLayoutBinding::inflate) {

    private val postDetailsViewModel : PostDetailsViewModel by viewModels()

    private lateinit var imageRecyclerViewAdapter: ImageRecyclerViewAdapter

    override fun bind() {
        getPostDetails()
        bindImageRecyclerView()
    }

    override fun bindViewActionListeners() {
    }

    override fun bindObservers() {
        bindDetailsFlow()
    }

    private fun getPostDetails() {
        val id = arguments?.let { PostDetailsFragmentArgs.fromBundle(it).id } ?: return
        postDetailsViewModel.onEvent(PostDetailsEvent.GetPostDetails(id))
    }

    private fun bindImageRecyclerView() {
        imageRecyclerViewAdapter = ImageRecyclerViewAdapter()
        binding.imageContainer.apply {
            layoutManager = CustomLayoutManager()
            adapter = imageRecyclerViewAdapter
        }
    }

    private fun bindDetailsFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                postDetailsViewModel.detailsState.collect { state ->
                    handleState(state)
                }
            }
        }
    }

    private fun handleState(state : DetailsState) {
        binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE

        state.error?.let { errorMessage ->
            showErrorMessage(errorMessage)
        }

        state.post?.let { post ->
            bindPostDetails(post)
            imageRecyclerViewAdapter.submitList(post.images)
        }
    }

    private fun bindPostDetails(post: PostPresentation) {
        with(binding) {
            Glide.with(this@PostDetailsFragment)
                .load(post.profile)
                .transform(CenterCrop(), RoundedCorners(300))
                .into(icUserIcon)

            tvUserName.text = post.userName
            tvDate.text = post.postDate
            tvDescription.text = post.title
            tvComments.text = post.comments
            tvLikes.text = post.likes
        }
    }

    private fun showErrorMessage(message : String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}