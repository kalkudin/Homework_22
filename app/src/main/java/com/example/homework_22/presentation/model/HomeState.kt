package com.example.homework_22.presentation.model

data class HomeState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val stories: List<StoryPresentation> = emptyList(),
    val posts: List<PostPresentation> = emptyList()
)