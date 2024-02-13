package com.example.homework_22.presentation.mapper

import com.example.homework_22.domain.model.Post
import com.example.homework_22.presentation.model.PostPresentation
import java.text.SimpleDateFormat
import java.util.Locale

fun Post.toPresentation(): PostPresentation {
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    val dateLong = postDate.toLongOrNull() ?: 0L
    val formattedDate = dateFormat.format(java.util.Date(dateLong * 1000))

    return PostPresentation(
        id = id,
        images = images,
        title = title,
        comments = comments,
        likes = likes,
        firstName = firstName,
        lastName = lastName,
        profile = profile,
        postDate = formattedDate
    )
}
