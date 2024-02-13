package com.example.homework_22.presentation.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_22.databinding.FragmentHomeLayoutBinding
import com.example.homework_22.presentation.adapter.HostRecyclerViewAdapter
import com.example.homework_22.presentation.base.BaseFragment
import com.example.homework_22.presentation.event.HomeEvent
import com.example.homework_22.presentation.model.HomeState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeLayoutBinding>(FragmentHomeLayoutBinding::inflate) {

    private val homeViewModel : HomeViewModel by viewModels()

    private lateinit var hostAdapter: HostRecyclerViewAdapter

    override fun bind() {
        bindStories()
        bindPosts()
        bindHostRecyclerViewAdapter()
    }

    override fun bindViewActionListeners() {

    }

    override fun bindObservers() {
        bindHomeFlow()
    }

    private fun bindStories() {
        homeViewModel.onEvent(HomeEvent.GetStories)
    }

    private fun bindPosts() {
        homeViewModel.onEvent(HomeEvent.GetPosts)
    }

    private fun bindHostRecyclerViewAdapter() {
        hostAdapter = HostRecyclerViewAdapter(emptyList(), emptyList()) // Initialize with empty lists
        binding.hostRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = hostAdapter
        }
    }

    private fun bindHomeFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.homeState.collect { state ->
                    handleState(state)
                }
            }
        }
    }

    private fun handleState(state : HomeState) {
        binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE

        state.error?.let { errorMessage ->
            showErrorMessage(errorMessage)
        }

        hostAdapter.updateData(state.stories, state.posts)
    }

    private fun showErrorMessage(message : String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}