package com.example.homework_22.data.remote.mapper

import com.example.homework_22.data.remote.model.PostDto
import com.example.homework_22.domain.model.Post

fun PostDto.toDomain(): Post {
    return Post(
        id = id,
        images = images ?: emptyList(),
        title = title,
        comments = comments,
        likes = likes,
        firstName = owner.firstName ?: "",
        lastName = owner.lastName ?: "",
        profile = owner.profile ?: "",
        postDate = owner.postDate
    )
}