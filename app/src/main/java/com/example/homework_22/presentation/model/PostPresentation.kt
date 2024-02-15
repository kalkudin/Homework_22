package com.example.homework_22.presentation.model

data class PostPresentation(
    val id: Int,
    val images: List<String>,
    val title: String,
    val comments: String,
    val likes: String,
    val userName: String,
    val profile: String,
    val postDate: String
)