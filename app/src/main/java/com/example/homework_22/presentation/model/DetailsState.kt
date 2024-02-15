package com.example.homework_22.presentation.model

data class DetailsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val post: PostPresentation? = null
)