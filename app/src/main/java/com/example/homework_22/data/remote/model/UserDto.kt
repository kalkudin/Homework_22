package com.example.homework_22.data.remote.model

import com.squareup.moshi.Json

data class UserDto(
    @Json(name = "first_name")
    val firstName: String?,
    @Json(name = "last_name")
    val lastName: String?,
    @Json(name = "profile")
    val profile: String?,
    @Json(name = "post_date")
    val postDate: String
)