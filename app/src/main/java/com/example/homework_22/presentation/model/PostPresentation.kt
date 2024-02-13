package com.example.homework_22.presentation.model

data class PostPresentation(
    val id: Int,
    val images: List<String>,
    val title: String,
    val comments: Int,
    val likes: Int,
    val firstName: String,
    val lastName: String,
    val profile: String,
    val postDate: String
)