package com.example.homework_22.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.usecase.GetPostsUseCase
import com.example.homework_22.domain.usecase.GetStoriesUseCase
import com.example.homework_22.presentation.event.HomeEvent
import com.example.homework_22.presentation.mapper.mapErrorToMessage
import com.example.homework_22.presentation.mapper.toPresentation
import com.example.homework_22.presentation.model.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getStoriesUseCase: GetStoriesUseCase,
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    private val _navigationFlow = MutableSharedFlow<HomeNavigationEvent>()
    val navigationFlow : SharedFlow<HomeNavigationEvent> = _navigationFlow.asSharedFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetStories -> getStories()
            is HomeEvent.GetPosts -> getPosts()
            is HomeEvent.NavigateToDetails -> navigateToDetails(id = event.id)
        }
    }

    private fun getStories() {
        viewModelScope.launch {
            getStoriesUseCase().collect { result ->
                Log.d("HomeViewModel", "getStories result: $result")
                _homeState.update { currentState ->
                    when (result) {
                        is Resource.Loading -> currentState.copy(isLoading = true)
                        is Resource.Success -> {
                            val stories = result.data.map { it.toPresentation() }
                            currentState.copy(stories = stories, isLoading = false)
                        }
                        is Resource.Error -> {
                            val errorMessage = mapErrorToMessage(result.errorType)
                            Log.e("HomeViewModel", "Error fetching stories: $errorMessage")
                            currentState.copy(error = errorMessage, isLoading = false)
                        }
                    }
                }
            }
        }
    }

    private fun getPosts() {
        viewModelScope.launch {
            getPostsUseCase().collect { result ->
                Log.d("HomeViewModel", "getPosts result: $result")
                _homeState.update { currentState ->
                    when (result) {
                        is Resource.Loading -> currentState.copy(isLoading = true)
                        is Resource.Success -> {
                            val posts = result.data.map { it.toPresentation() }
                            currentState.copy(posts = posts, isLoading = false)
                        }
                        is Resource.Error -> {
                            val errorMessage = mapErrorToMessage(result.errorType)
                            Log.e("HomeViewModel", "Error fetching posts: $errorMessage")
                            currentState.copy(error = errorMessage, isLoading = false)
                        }
                    }
                }
            }
        }
    }

    private fun navigateToDetails(id : Int) {
        viewModelScope.launch {
            _navigationFlow.emit(HomeNavigationEvent.NavigateToDetails(id = id))
        }
    }
}

sealed class HomeNavigationEvent() {
    data class NavigateToDetails(val id : Int) : HomeNavigationEvent()
}