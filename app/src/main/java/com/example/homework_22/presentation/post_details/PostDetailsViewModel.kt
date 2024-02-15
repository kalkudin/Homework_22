package com.example.homework_22.presentation.post_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.usecase.GetPostDetailsUseCase
import com.example.homework_22.presentation.event.PostDetailsEvent
import com.example.homework_22.presentation.mapper.mapErrorToMessage
import com.example.homework_22.presentation.mapper.toPresentation
import com.example.homework_22.presentation.model.DetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val getPostDetailsUseCase: GetPostDetailsUseCase
) : ViewModel() {

    private val _detailsState = MutableStateFlow(DetailsState())
    val detailsState: StateFlow<DetailsState> = _detailsState.asStateFlow()

    fun onEvent(event: PostDetailsEvent) {
        when (event) {
            is PostDetailsEvent.GetPostDetails -> getPostDetails(id = event.id)
        }
    }

    private fun getPostDetails(id: Int) {
        viewModelScope.launch {
            getPostDetailsUseCase(id = id).collect() { result ->
                Log.d("DetailsViewModel", "getPosts result: $result")
                _detailsState.update { currentState ->
                    when (result) {
                        is Resource.Success -> {
                            val post = result.data.toPresentation()
                            currentState.copy(post = post, isLoading = false)
                        }

                        is Resource.Error -> {
                            val errorMessage = mapErrorToMessage(result.errorType)
                            Log.e("HomeViewModel", "Error fetching posts: $errorMessage")
                            currentState.copy(error = errorMessage, isLoading = false)
                        }

                        is Resource.Loading -> {
                            currentState.copy(isLoading = true)
                        }
                    }
                }
            }
        }
    }
}