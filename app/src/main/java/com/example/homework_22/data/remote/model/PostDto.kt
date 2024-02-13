package com.example.homework_22.data.remote.model

data class PostDto(
    val id: Int,
    val images: List<String>?,
    val title: String,
    val comments: Int,
    val likes: Int,
    val owner: UserDto,
)